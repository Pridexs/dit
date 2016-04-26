/*
 * Mobile Robotics - Assignment 2
 *
 * Dublin Institute of Technology
 * Students:
 *  - Alexandre Maros - D14128553
 *  - Fábio Dayrell Rosa - D14128448
 *
 */

package assignment2;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

import assignment2.Robot;

public class DetectCloseObject implements Behavior {
    private boolean suppressed;
    private static Robot robot;

    public DetectCloseObject(Robot r) {
        suppressed = false;
        robot = r;
    }

    public boolean takeControl() {
        return robot.sonic.getDistance() < 25 && !robot.isCloseToYWall();
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        suppressed = false;
        
        int nAction = 0;

        /* Making a switch statement so the code becomes more readable
         * since we do not need to have a while loop after every
         * action
         * 
         * Rotate 90 degrees -> Make an Arc of radius 8 -> Rotate -90 Degrees
         */
        
        while( !suppressed && nAction < 3 ) {
            if ( !robot.pilot.isMoving() ){
                switch (nAction) {
                    case 0:
                        robot.pilot.rotate(-90, true);
                        break;
                    case 1:
                        robot.pilot.arc(7, 180, true);;
                        break;
                    case 2:
                        robot.pilot.rotate(-90, true);
                        break;
                }
                nAction++;
            }
            
            if (robot.enteredCarpet()) {
                Sound.twoBeeps();
                LCD.clear();
                LCD.drawString("Carpet!", 0, 0);
            } else if (robot.leftCarpet()) {
                LCD.clear();
            }
        }
        
        // This while is just to wait for the robot to complete
        // the final action (if it is not suppressed)
        while ( !suppressed && robot.pilot.isMoving() ) { }
        
        robot.movePY(12);
        
        robot.pilot.stop();
    }
}
