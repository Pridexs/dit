package assignment1;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

// Default behavior

/*
 * - The variable bumped is used to ensure that the robot will not return to
 *   this state after it hits something (Final State).
 */

public class MoveForward implements Behavior {
    private boolean suppressed, bumped;
    private static Robot robot;

    public MoveForward(Robot r) {
        suppressed = false;
        bumped = false;
        robot = r;
    }

    public boolean takeControl() {
        // This is the default behavior, so if the escape button is not
        // pressed, keep koing forward.
        return ( !Button.ESCAPE.isDown() && !bumped );
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
        bumped = robot.bump.isPressed();
    }
}
