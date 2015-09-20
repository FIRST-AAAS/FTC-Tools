

package org.aaas.stem.first.ftc.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.aaas.stem.first.ftc.hardware.ClockComponent;
import org.aaas.stem.first.ftc.hardware.CompassSensorComponent;
import org.aaas.stem.first.ftc.hardware.DcMotorComponent;

public class AutoRotateOp extends AAASOpMode {

  // we will consider our goal reached if we are +/- ERROR_MARGIN degrees
  final static double ERROR_MARGIN = 5.0; // in degrees

  // how long to hold our goal position
  final static double HOLD_POSITION = 3.0; // in seconds

  // wheel speed while moving to our goal
  final static double MOTOR_POWER = 0.2; // scale from 0 to 1

  // each goal direction in degrees
  final static double GOALS[] = { 0.0, 90.0, 180.0, 270.0 };

  // index of the current goal
  int currentGoal = 0;

  // when paused time as passed, we will proceed to the next goal
  double pauseTime = 0.0;

  CompassSensorComponent compass;
  DcMotorComponent motorRight;
  DcMotorComponent motorLeft;
  ClockComponent clockComponent;


  @Override
    public boolean isAutonomous() {
        return true;
    }

    @Override
  public void init() {
    compass = new CompassSensorComponent(getHardwareManager(),"compass");
    motorRight = new DcMotorComponent(getHardwareManager(),"right");
    motorLeft = new DcMotorComponent(getHardwareManager(),"left");
    clockComponent = new ClockComponent(getHardwareManager(),"clock");

    motorRight.setDirection(DcMotor.Direction.REVERSE);

    // calculate how long should we hold the current position
    pauseTime = clockComponent.getElapsedSeconds() + HOLD_POSITION;
  }

  @Override
  public void loop() {

    // make sure pauseTime has passed before we take any action
    if ( clockComponent.getElapsedSeconds() > pauseTime) {

      // get our current direction
      double facing = compass.getDirection();

      // have we reached our goal direction?
      if (hasReachedDegree(GOALS[currentGoal], facing) == false) {
        // no, move toward our goal
        DbgLog.msg("moving from " + facing + " toward " + GOALS[currentGoal]);
        if ( this.getHardwareManager().isDriverDebugMode()) {
              sendTelemetry("has Reached Degree" ,"moving from " + facing + " toward " + GOALS[currentGoal]);
        }

          // rotate the robot towards our goal direction
        motorRight.setPower(-MOTOR_POWER);
        motorLeft.setPower(MOTOR_POWER);

      } else {
        // yes, set a new goal
        DbgLog.msg("holding " + facing);
        if ( this.getHardwareManager().isDriverDebugMode()) {
              sendTelemetry("has NOT Reached Degree" ,"holding " + facing);
        }

        motorRight.setPower(0.0);
        motorLeft.setPower(0.0);

        // set a new goal direction
        currentGoal = (currentGoal + 1) % GOALS.length;

        // set a new pauseTime
        pauseTime =  clockComponent.getElapsedSeconds() + HOLD_POSITION;
      }
    }
    else {
        if ( this.getHardwareManager().isDriverDebugMode()) {
            sendTelemetry("Clock" , "Elapsed Time: " + clockComponent.getElapsedSeconds() + " has not exceeded pause time: " + pauseTime);
        }
    }
  }

  // check if we reached our target degree, +/- ERROR_MARGIN
  private boolean hasReachedDegree(double target, double current) {
    return (
        (Math.abs(target - (current      ))) < ERROR_MARGIN ||
        (Math.abs(target - (current - 360))) < ERROR_MARGIN ||
        (Math.abs(target - (current + 360))) < ERROR_MARGIN);
  }

  @Override
  public void stop() {

  }

}
