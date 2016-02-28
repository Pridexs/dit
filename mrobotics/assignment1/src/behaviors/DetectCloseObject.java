package assignment1;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

import assignment1.Robot;

public class DetectCloseObject implements Behavior {
    private boolean suppressed, wasTriggered;
    private static Robot robot;

    public DetectCloseObject(Robot r) {
        suppressed = false;
        robot = r;
    }

    public boolean takeControl() {
        return !suppressed;
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        robot.pilot.rotate(90, true);
        while( !suppressed && robot.pilot.isMoving() ) {
            // Wait
        }
    }
}
