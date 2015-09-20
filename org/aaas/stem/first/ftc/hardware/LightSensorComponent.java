package org.aaas.stem.first.ftc.hardware;

import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.LightSensor;

public class LightSensorComponent extends SensorComponent {

    private LightSensor lightSensor;


    public LightSensorComponent(HardwareManager hardwareManager, String componentName) {

        super(hardwareManager , componentName);

        if (!isDriverDebugMode() ) {
            lightSensor = getHardwareManager().getHardwareMap().lightSensor.get(componentName);
        }
        else {
            getDebugMap().put("ledEnabled" , "true");
            addDebugProperty("ledEnabled", "type", "Boolean");

            getDebugMap().put("lightDetectedRaw", "0.0");
            addDebugProperty("lightDetectedRaw", "type", "Double");
            addDebugProperty("lightDetectedRaw", "min", "0");
            addDebugProperty("lightDetectedRaw", "max", "1");
            addDebugProperty("lightDetectedRaw", "decimalSpan", "10");


            getDebugMap().put("status", "");
            addDebugProperty("status", "type", "String");

        }


    }


    public double getLightDetectedRaw() {

        if (!isDriverDebugMode() ) {
            return lightSensor.getLightDetectedRaw();
        }
        else {
            return Double.valueOf((String)getDebugMap().get("lightDetectedRaw"));

        }

    }

    public String status() {

        if (!isDriverDebugMode() ) {
            return lightSensor.status();
        }
        else {
            return (String)getDebugMap().get("status");

        }

    }

    public void enableLed(boolean aBool) {

        if (!isDriverDebugMode() ) {
             lightSensor.enableLed(aBool);
        }
        else {
             getDebugMap().put("ledEnabled" , Boolean.toString(aBool));
        }

    }


}
