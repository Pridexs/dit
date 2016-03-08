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

import assignment1.Robot;

public class DetectCloseObject implements Behavior {
    private boolean suppressed, wasTriggered;
    private static Robot robot;

    public DetectCloseObject(Robot r) {
        suppressed = false;
        wasTriggered = false;
        robot = r;
    }

    public boolean takeControl() {
        // We chose this behavior to run only once so it does not
        // interfere with the bumper in the last part, that is
        // why the !wasTriggered variable is used.
        return ( robot.sonic.getDistance() < 30 );
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        suppressed = false;
        wasTriggered = true;
        int nAction = 0;

        // Making a switch statement so the code becomes more readable
        // since we do not need to have a while loop after every
        // action
        while( !suppressed && nAction < 3 ) {
            if ( !robot.pilot.isMoving() && !Button.ESCAPE.isDown() ){
                switch (nAction) {
                    case 0:
                        robot.pilot.rotate(180, true);
                        break;
                    case 1:
                        robot.pilot.travel(20, true);
                        break;
                    case 2:
                        robot.pilot.rotate(90, true);
                        break;
                }
                nAction++;
            }
        }

        // This while is just to wait for the robot to complete
        // the final action (if it is not suppressed)
        while ( !suppressed && robot.pilot.isMoving() && !Button.ESCAPE.isDown() ) { }
        robot.pilot.stop();
    }
}
