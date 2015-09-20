package org.aaas.stem.first.ftc.opmodes;



import org.aaas.stem.first.ftc.RobotControllerDriverActivity;
import org.aaas.stem.first.ftc.hardware.HardwareManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;
import java.util.List;


public abstract class AAASOpMode extends OpMode {


    private HardwareManager hardwareManager;
    private List<String>  debugTelemetry = new ArrayList<String>() ;


    public abstract boolean isAutonomous();

    public HardwareManager getHardwareManager() {
        if (hardwareManager == null) {

            this.hardwareManager = new HardwareManager(hardwareMap,this);
        }
        return hardwareManager;
    }



    public void initInDebugMode(RobotControllerDriverActivity robotControllerDriverActivity) {

        getHardwareManager().setDriverDebugMode( robotControllerDriverActivity);
        this.init();


    }

    public void resetDebugTelemetry () {

        debugTelemetry.clear(); ;
    }

    public List<String> getDebugTelemetry() {
        return debugTelemetry;
    }

    protected void sendTelemetry(String title , String message) {
        if (  ! getHardwareManager().isDriverDebugMode() ) {
            telemetry.addData(title,message);
        }
        else {
            debugTelemetry.add(title + " : " + message);
        }

    }

    protected void sendTelemetry(String title , double message) {
        sendTelemetry(title , Double.toString(message));
    }
    protected void sendTelemetry(String title , float message) {
        sendTelemetry(title , Float.toString(message));
    }



}