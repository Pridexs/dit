package assignment1;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {
    public static DifferentialPilot pilot;
    public static UltrasonicSensor sonic;
    public static LightSensor light;
    public static SoundSensor sound;

    public Robot() {
        pilot = new DifferentialPilot(2.25f, 4.25f, Motor.A, Motor.C);
        pilot.setTravelSpeed(4.0);

        sonic = new UltrasonicSensor(SensorPort.S3);
        light = new LightSensor(SensorPort.S2);
        sound = new SoundSensor(SensorPort.S1);
    }
}
