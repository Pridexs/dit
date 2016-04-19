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
 *  nxjlink assignment1.Assignment1 -o Assignment1.nxj
 *  nxjupload -u -r Assignment1.nxj
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
            while (robot.sonic.getDistance() > 35) {
                // Move until reaches wall
            }
            robot.pilot.stop();
            if (i % 2 == 0) {
                robot.setSizeY(robot.pilot.getMovementIncrement());
            } else {
                robot.setSizeX(robot.pilot.getMovementIncrement());
            }
            robot.pilot.rotate(90);
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
        //Behavior b2 = new DetectLeftCarpet(robot);
        //Behavior b3 = new DetectEnteredCarpet(robot);
        Behavior b4 = new DetectCloseObject(robot);
        Behavior b5 = new DetectWall(robot);
        Behavior b6 = new Stop(robot);
        Behavior [] bArray = {b1, b4, b5, b6};

        Arbitrator arby = new Arbitrator(bArray);
        arby.start();
    }
}
