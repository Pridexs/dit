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
        return robot.sonic.getDistance() < 11;
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        suppressed = false;

        robot.pilot.arc(45, 180, true);
        while (robot.pilot.isMoving() || !suppressed) {
            // Wait
        }
        
        if (!suppressed) {
            robot.pilot.rotate(-90, true);
        }
        
        while (robot.pilot.isMoving() || !suppressed) {
            // Wait
        }
        
        robot.pilot.stop();
    }
}
