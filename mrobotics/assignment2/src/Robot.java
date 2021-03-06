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
    
    // Variable to store the Movement Increment
    private float movementIncrement;
    
    // Variable to control which direction to turn
    // -1 Clockwise 
    // 1 Counter-Clockwise
    private int turnDirection;
    
    // Variable to control the carpet detection
    private boolean isInCarpet;
    private int floorLightValue;
    private int lightError;
    
    // Variables for debug purposes
    private float debugCounter;

    public Robot() {
        movementIncrement = 0;
        isInCarpet = false;
        turnDirection = -1;
        debugCounter = 0;
        lightError = 40;
        sizeX = 0;
        sizeY = 0;
        px = 0;
        py = 0;
        
        pilot = new DifferentialPilot(2.3f, 4.35f, Motor.A, Motor.C);
        pilot.setTravelSpeed(6.0);

        light   = new LightSensor(SensorPort.S4);
        sonic   = new UltrasonicSensor(SensorPort.S2);
        bump    = new TouchSensor(SensorPort.S1);
    }
    
    public void setFloorLightValue(int flv) {
        this.floorLightValue = flv;
    }

    /*
     * py = current position of the robot in the y axis
     * movementIncrement = how much the robot moved since he last started moving
     * sizeY = size of wall Y (length))
     */
    public boolean isCloseToYWall() {  
        if (py + 2 + movementIncrement > sizeY) {
            return true;
        }
        return false;
    }
    
    /*
     * px = current position of the robot in the x axis
     * sizeX = size of wall X (length))
     */
    public boolean isCloseToXWall() {
        if (px + 2 > sizeX) {
            // This is to ensure that he finish the last run.
            if (isCloseToYWall()) {
                return true;
            }
            return false;
        }
        return false;
    }

    public float getSizeX() {
        return this.sizeX;
    }

    public float getSizeY() {
        return this.sizeY;
    }

    public void setSizeX(float x) {
        this.sizeX = x;
    }

    public void setSizeY(float y) {
        this.sizeY = y;
    }

    public float getPX() {
        return this.px;
    }

    public float getPY() {
        return this.py;
    }

    public void movePX(float x) {
        this.px = this.px + x;
    }

    public void movePY(float y) {
        this.py = this.py + y;
    }

    public void setPX(float x) {
        this.px = x;
    }

    public void setPY(float y) {
        this.py = y;
    }
    
    public int getTurnDirection() {
        int aux = this.turnDirection;
        this.turnDirection = this.turnDirection * -1;
        return aux;
    }
    
    public void setMovementIncrement(float mi) {
        this.movementIncrement = mi;
    }
    
    public void resetMovementIncrement() {
        this.py += this.movementIncrement;
        this.movementIncrement = 0;
    }
    
    public void setIsInCarpet(boolean isc) {
        this.isInCarpet = isc;
    }
    
    
    /*
     * If the normalized Light Value is higher than the floorLightValue + lightError
     * and if the normalized light value is lower than the floorLightValue - lightError
     * the robot is in the carpet.
     * The lightError was used to have a margin of error.
     */
    public boolean enteredCarpet() {
        if (!isInCarpet) {
            int nlv = light.getNormalizedLightValue();
            if (nlv > floorLightValue + lightError 
               || nlv < floorLightValue - lightError) {
                this.isInCarpet = true;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /*
     * Same thing as the previous one, but detecting if he left the carpet and
     * is in the value of the floorLightValue again.
     */
    public boolean leftCarpet() {
        if (isInCarpet) {
            int nlv = light.getNormalizedLightValue();
            if (nlv > floorLightValue - lightError 
               && nlv < floorLightValue + lightError) {
                isInCarpet = false;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
