/*
 * Mobile Robotics - Assignment 2
 *
 * Dublin Institute of Technology
 * Students:
 *  - Alexandre Maros - D14128553
 *  - Fábio Dayrell Rosa - D14128448
 *
 * To Compile run these commands in succession
 *  nxjc -d . behaviors/*.java *.java
 *  nxjlink assignment2.Assignment2 -o Assignment2.nxj
 *  nxjupload -u -r Assignment2.nxj
 */


package assignment2;

import lejos.nxt.*;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.subsumption.Arbitrator;

import assignment2.*;

public class Assignment2 {
    public static void main (String[] args) throws Exception {
        // Initial sleep, otherwise the robot will skip this first
        // while if runned directly through the robot.
        Thread.sleep(200);
        
        LCD.clear();
        LCD.drawString("Assignment2", 0, 0);
        while ( true ) {
            if ( Button.ENTER.isDown() || Button.LEFT.isDown()
                || Button.RIGHT.isDown() || Button.ESCAPE.isDown() ) {
                break;
            }
        }

        Robot robot = new Robot();
        
        /*
         * ROOM RECOGNITION
         * Move until finds a wall -> rotate -> repeat 4 times
         */
        
        for (int i = 0; i < 4; i++) {
            robot.pilot.forward();
            while (robot.sonic.getDistance() > 25) {
                // Move until reaches wall
            }
            robot.pilot.stop();
            if (i % 2 == 0) {
                robot.setSizeY(robot.pilot.getMovementIncrement());
            } else {
                robot.setSizeX(robot.pilot.getMovementIncrement());
            }
            robot.pilot.rotate(-90);
        }
        // Find floor color
        robot.setFloorLightValue(robot.light.getNormalizedLightValue());
        
        /*
         * END ROOM RECOGNITION
         */


        /*
         * Declaration of behaviours.
         * b1 = Lowest Priority
         * b4 = Highest Priority
         */
        Behavior b1 = new MoveForward(robot);
        Behavior b2 = new DetectCloseObject(robot);
        Behavior b3 = new DetectWall(robot);
        Behavior b4 = new Stop(robot);
        Behavior [] bArray = {b1, b2, b3, b4};

        Arbitrator arby = new Arbitrator(bArray);
        arby.start();
    }
}
