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
        sizeX = 0;
        sizeY = 0;
        px = 0;
        py = 0;
        turnDirection = 1;
        movementIncrement = 0;
        isInCarpet = false;
        lightError = 40;
        
        debugCounter = 0;

        pilot = new DifferentialPilot(2.25f, 4.25f, Motor.A, Motor.C);
        pilot.setTravelSpeed(4.0);

        light   = new LightSensor(SensorPort.S2);
        sonic   = new UltrasonicSensor(SensorPort.S3);
        bump    = new TouchSensor(SensorPort.S4);
    }
    
    public void setFloorLightValue(int flv) {
        this.floorLightValue = flv;
    }

    public boolean isCloseToYWall() {
        
        // For DEBUG Purposes:
        if (debugCounter >= 5) {
            debugCounter = 0;
            LCD.clear();
            LCD.drawString("sizeX", 0, 0);
            LCD.drawInt(Math.round(sizeX), 5, 0);
            
            LCD.drawString("sizeY", 0, 1);
            LCD.drawInt(Math.round(sizeY), 5, 1);
            
            
            LCD.drawString("px", 0, 2);
            LCD.drawInt(Math.round(px), 3, 2);
            
            LCD.drawString("py", 0, 3);
            LCD.drawInt(Math.round(py), 3, 3);
            
            LCD.drawString("mi", 0, 4);
            LCD.drawInt(Math.round(movementIncrement), 2, 4);
        }
        debugCounter++;
        
        if (py + 20 > sizeY + movementIncrement) {
            return true;
        }
        return false;
    }

    public boolean isCloseToXWall() {
        if (px + 20 > sizeX) {
            return true;
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
    
    public boolean enteredCarpet() {
        if (!isInCarpet) {
            int nlv = light.getNormalizedLightValue();
            if (nlv > floorLightValue + lightError 
               || nlv < floorLightValue - lightError) {
                isInCarpet = true;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
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
