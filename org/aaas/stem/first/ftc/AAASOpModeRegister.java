
package org.aaas.stem.first.ftc;


import org.aaas.stem.first.ftc.opmodes.AAASOpMode;
import org.aaas.stem.first.ftc.opmodes.AutoRotateOp;
import org.aaas.stem.first.ftc.opmodes.CompassCalibration;
import org.aaas.stem.first.ftc.opmodes.IrSeekerOp;
import org.aaas.stem.first.ftc.opmodes.K9IrSeeker;
import org.aaas.stem.first.ftc.opmodes.K9Line;
import org.aaas.stem.first.ftc.opmodes.K9TankDrive;
import org.aaas.stem.first.ftc.opmodes.K9TeleOp;

import java.util.Arrays;
import java.util.List;


/**
 *  Op Mode classes for registration
 */
public class AAASOpModeRegister {

    public static List<Class<? extends AAASOpMode>> getOpModeClasses() {
         return Arrays.asList(
                 AutoRotateOp.class,
                 CompassCalibration.class,
                 IrSeekerOp.class,
                 K9IrSeeker.class,
                 K9Line.class,
                 K9TankDrive.class,
                 K9TeleOp.class
         );
    }

}
