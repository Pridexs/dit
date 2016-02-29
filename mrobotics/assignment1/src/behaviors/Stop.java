package assignment1;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

import assignment1.Robot;

public class Stop implements Behavior {
    private boolean suppressed, wasTriggered;
    private static Robot robot;

    public Stop(Robot r) {
        suppressed = false;
        wasTriggered = false;
        robot = r;
    }

    public boolean takeControl() {
        return ( !wasTriggered && robot.sonic.getDistance() <25 );
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        wasTriggered = true;
        int nAction = 0;

        // Making a switch statement so the code becomes more readable
        // since we do not need to have a while loop after every
        // action
        while( !suppressed && nAction < 3 ) {
            if ( !robot.pilot.isMoving() ){
                switch (nAction) {
                    case 0:
                        robot.pilot.rotate(180, true);
                        break;
                    case 1:
                        robot.pilot.travel(20, true);
                        break;
                    case 2:
                        robot.pilot.rotate(-90, true);
                        break;
                }
                nAction++;
            }
        }

        // This while is just to wait for the robot to complete
        // the final action (if it is not suppressed)
        while ( !suppressed && robot.pilot.isMoving() ) { }
        robot.pilot.stop();
    }
}
