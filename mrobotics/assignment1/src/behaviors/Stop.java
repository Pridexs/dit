package assignment1;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

import assignment1.Robot;

public class Stop implements Behavior {
    private boolean suppressed;
    private static Robot robot;

    public Stop(Robot r) {
        suppressed = false;
        robot = r;
    }

    public boolean takeControl() {
        return robot.bump.isPressed();
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        LCD.clear();
        LCD.drawString("STOP!", 0, 0);

        while( !suppressed && !Button.ESCAPE.isDown() ) {

        }
        System.exit(0);
        LCD.clear();
    }
}
