/*
 * Mobile Robotics - Assignment 1
 *
 * Dublin Institute of Technology
 * Students:
 *  - Alexandre Maros - D14128553
 *  - Fábio Dayrell Rosa - D14128448
 *
 */

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
    private boolean suppressed;
    private static Robot robot;

    public MoveForward(Robot r) {
        suppressed = false;
        robot = r;
    }

    public boolean takeControl() {
        // This is the default behavior, so if the escape button is not
        // pressed, keep koing forward.
        return true;
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        suppressed = false;
        robot.pilot.forward();
        while( !suppressed ) {
            // Wait
        }
        robot.pilot.stop();
    }
}
