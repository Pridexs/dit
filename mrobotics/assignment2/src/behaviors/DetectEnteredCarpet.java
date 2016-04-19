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

public class DetectEnteredCarpet implements Behavior {
    private boolean suppressed;
    private static Robot robot;

    public DetectEnteredCarpet(Robot r) {
        suppressed = false;
        robot = r;
    }

    public boolean takeControl() {
        return robot.enteredCarpet();
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        suppressed = false;

        
        LCD.drawString("DETECTENTEREDCARPET", 0, 5);
        
        Sound.beep();
        LCD.clear();
        LCD.drawString("Carpet!", 0, 0);
    }
}
