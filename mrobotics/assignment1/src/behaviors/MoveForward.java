package assignment1;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

// Default behavior
public class MoveForward implements Behavior {
    private boolean suppressed;
    private static Robot robot;

    public MoveForward(Robot r) {
        suppressed = false;
        robot = r;
    }

    public boolean takeControl() {
        // This is the default behavior, so if the escape button is not
        // pressed, keep koing forward.
        return !Button.ESCAPE.isDown();
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        suppressed = false;
        robot.pilot.forward();
        while( robot.pilot.isMoving() && !suppressed ) {
            Thread.yield();
        }
        robot.pilot.stop();
    }
}
