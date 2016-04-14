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

/*
 * Robot class, so we dont instantiate several Sensors in each behaviour.
 */

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {
    public static DifferentialPilot pilot;
    public static UltrasonicSensor sonic;
    public static LightSensor light;
    public static TouchSensor bump;

    // Size of the room
    private float sizeX, sizeY;

    // Position of the robot
    private float px, py;

    public Robot() {
        sizeX = 0;
        sizeY = 0;
        px = 0;
        py = 0;

        pilot = new DifferentialPilot(2.25f, 4.25f, Motor.A, Motor.C);
        pilot.setTravelSpeed(4.0);

        light = new LightSensor(SensorPort.S2);
        sonic = new UltrasonicSensor(SensorPort.S3);
        bump = new TouchSensor(SensorPort.S4);
    }

    public boolean  isCloseToYWall() {
        if (py + 20 > sizeY) {
            return true;
        }
    }

    public boolean isCloseToXWall() {
        if (px + 20 > sizeX) {
            return true;
        }
    }

    public getSizeX() {
        return this.sizeX;
    }

    public getSizeY() {
        return this.sizeY;
    }

    public setSizeX(float x) {
        this.sizeX = x;
    }

    public setSizeY(float y) {
        this.sizeY = y;
    }

    public float getPX() {
        return this.px;
    }

    public float getPY() {
        return this.pY;
    }

    public float movePX(float x) {
        this.px = this.px + x;
    }

    public float movePY(float y) {
        this.py = this.py + y;
    }

    public float setPX(float x) {
        this.px = x;
    }

    public float setPY(float y) {
        this.py = y;
    }
}
