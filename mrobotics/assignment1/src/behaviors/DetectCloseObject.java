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
        return robot.bump.isPressed();
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        wasTriggered = true;
        int nAction = 0;

        LCD.clear();
        LCD.drawString("STOP!", 0, 0);

        while( !suppressed && !Button.ESCAPE.isDown() ) {

        }
        
        LCD.clear();
    }
}
