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
        return ( robot.isCloseToYWall );
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        suppressed = false;

        robot.pilot.rotate(90, true);
        while (robot.pilot.isMoving() || !suppressed) {
            // Wait
        }

        robot.pilot.travel(7, true);
        while (robot.pilot.isMoving() || !suppressed) {
            // Wait
        }

        robot.pilot.rotate(90, true);
        while (robot.pilot.isMoving() || !suppressed) {
            // Wait
        }

        robot.pilot.stop();
    }
}
