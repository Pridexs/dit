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

public class DetectWall implements Behavior {
    private boolean suppressed;
    private static Robot robot;

    public DetectWall(Robot r) {
        suppressed = false;
        robot = r;
    }

    public boolean takeControl() {
        return ( robot.isCloseToYWall() );
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        suppressed = false;
        
        robot.setPY(0);
        
        // Should the robot turn clockwise or counter clockwise?
        double angle = 90 * robot.getTurnDirection();
        
        /* Making a switch statement so the code becomes more readable
         * since we do not need to have a while loop after every
         * action
         * 
         * Rotate 90 degrees -> Move 7 Units -> Rotate 90 Degrees
         */
        while( !suppressed && nAction < 3 ) {
            if ( !robot.pilot.isMoving() ){
                switch (nAction) {
                    case 0:
                        robot.pilot.rotate(angle, true);
                        break;
                    case 1:
                        robot.pilot.travel(7, true);
                        break;
                    case 2:
                        // If the robot is executing action 2 it means that
                        // action 1 was successful which means that the robot
                        // moved 7 units in the X axis.
                        robot.movePX(7);
                        robot.pilot.rotate(angle, true);
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
        
        robot.pilot.stop();
    }
}
