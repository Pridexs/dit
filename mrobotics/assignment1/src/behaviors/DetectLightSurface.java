package assignment1;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class DetectLightSurface implements Behavior {
    private boolean suppressed, wasTriggered;
    private static Robot robot;

    public DetectLightSurface(Robot r) {
        suppressed = false;
        wasTriggered = false;
        robot = r;
    }

    public boolean takeControl() {
        return ( !wasTriggered && robot.light.getNormalizedLightValue() > 400 );
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        wasTriggered = true;
        robot.pilot.rotate(-90, true);
        while( !suppressed && robot.pilot.isMoving() ) {
            // Wait
        }
    }
}
