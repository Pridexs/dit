/*
 * Mobile Robotics - Assignment 1
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


package assignment1;

import lejos.nxt.*;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.subsumption.Arbitrator;

import assignment1.*;

public class Assignment1 {
    public static void main (String[] args) throws Exception {
        // Initial sleep, otherwise the robot will skip this first
        // while if runned directly through the robot.
        Thread.sleep(200);

        LCD.clear();
        LCD.drawString("Assignment1", 0, 0);
        while ( true ) {
            if ( Button.ENTER.isDown() || Button.LEFT.isDown()
                || Button.RIGHT.isDown() || Button.ESCAPE.isDown() ) {
                break;
            }
        }

        Robot robot = new Robot();
        /*
         * Declaration of behaviours.
         * b1 = Lowest Priority
         * b5 = Highest Priority
         */
        Behavior b1 = new MoveForward(robot);
        Behavior b2 = new DetectCloseObject(robot);
        Behavior b3 = new DetectLightSurface(robot);
        Behavior b4 = new Stop(robot);
        Behavior b5 = new WaitForClap(robot);
        Behavior [] bArray = {b1, b2, b3, b4, b5};

        Arbitrator arby = new Arbitrator(bArray);
        arby.start();
    }
}
