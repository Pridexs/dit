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

        while( !suppressed && robot.sound.readValue() < 40) {
            Thread.yield();
        }

        wasTriggered = true;
        LCD.clear();
        LCD.drawString("Claps!", 0, 0);
    }
}
