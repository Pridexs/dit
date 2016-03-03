package assignment1;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class WaitForClap implements Behavior {
    private boolean suppressed, wasTriggered;
    private static Robot robot;

    public WaitForClap(Robot r) {
        suppressed = false;
        wasTriggered = false;
        robot = r;
    }

    public boolean takeControl() {
        // If it was not triggered yet, return true.
        return !wasTriggered;
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        LCD.clear();
        int sound;

        // The first few values read always seem to be very high, so we are
        // just reading the first 4 so it wont interfere with the system.
        for (int i = 0; i < 4; i++) {
            sound = robot.sound.readValue();
            try { Thread.sleep(50); } catch (Exception e) { }
        }

        sound = robot.sound.readValue();
        while( !suppressed && sound < 40) {
            sound = robot.sound.readValue();
        }

        wasTriggered = true;
        LCD.drawString("Claps!", 0, 0);
    }
}
