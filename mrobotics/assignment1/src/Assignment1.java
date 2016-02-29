package assignment1;

import lejos.nxt.*;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.subsumption.Arbitrator;

import assignment1.*;

public class Assignment1 {
    public static void main (String[] args) throws Exception {
        Thread.sleep(100);

        LCD.clear();
        LCD.drawString("Assignment1", 0, 0);
        while ( true ) {
            if ( Button.ENTER.isDown() || Button.LEFT.isDown()
                || Button.RIGHT.isDown() || Button.ESCAPE.isDown() ) {
                break;
            }
        }

        Robot robot = new Robot();
        Behavior b1 = new MoveForward(robot);
        Behavior b2 = new Stop(robot);
        Behavior b3 = new DetectCloseObject(robot);
        Behavior b4 = new DetectLightSurface(robot);
        Behavior b5 = new WaitForClap(robot);
        Behavior [] bArray = {b1, b2, b3, b4, b5};

        Arbitrator arby = new Arbitrator(bArray);
        arby.start();
    }
}
