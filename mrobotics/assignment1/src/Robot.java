package assignment1;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {
    public static DifferentialPilot pilot;
    public static UltrasonicSensor sonic;
    public static LightSensor light;
    public static SoundSensor sound;
    public static TouchSensor bump;

    public Robot() {
        pilot = new DifferentialPilot(2.25f, 4.25f, Motor.A, Motor.C);
        pilot.setTravelSpeed(4.0);

        sound = new SoundSensor(SensorPort.S1);
        light = new LightSensor(SensorPort.S2);
        sonic = new UltrasonicSensor(SensorPort.S3);
        bump = new TouchSensor(SensorPort.S4);
    }
}
