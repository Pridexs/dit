import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.Delay;
import lejos.nxt.NXTMotor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.util.Stopwatch;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.TouchSensor;
import lejos.nxt.SensorPort;

public class Lab4 {

    DifferentialPilot pilot;
    TouchSensor bump = new TouchSensor(SensorPort.S1);

    void checkBump()
    {
        while (pilot.isMoving())
        {
            if (bump.isPressed())
            {
                LCD.clear();
                LCD.drawString("BUMP", 0, 0);
                pilot.stop();
                while (true)
                {
                    if (Button.ENTER.isDown() || Button.LEFT.isDown() || Button.RIGHT.isDown())
                    {
                        LCD.clear();
                        break;
                    }
                }
            }
        }
    }

    void straightLine(double length)
    {
        pilot.travel(length, true);
        checkBump();
    }

    void rectangle(double width, double height)
    {
        for (int i = 0; i < 2; i++)
        {
            pilot.travel(width, true);
            checkBump();
            pilot.rotate(90, true);
            checkBump();
            pilot.travel(height, true);
            checkBump();
            pilot.rotate(90, true);
            checkBump();
        }
    }

    void doArc(double radius, double angle)
    {
        pilot.arc(radius, angle, true);
        checkBump();
    }

    public static void main (String[] args) throws Exception {
        // Step 1
        LCD.clear();
        LCD.drawString("Travel Tests", 0, 0);

        while (true)
        {
            if (Button.ENTER.isDown() || Button.LEFT.isDown() || Button.RIGHT.isDown())
            {
                LCD.clear();
                break;
            }
        }

        Lab4 lab4 = new Lab4();
        lab4.pilot = new DifferentialPilot(2.25f, 4.25f, Motor.A, Motor.C);
        lab4.pilot.setTravelSpeed(4.0);
        lab4.straightLine(20);
        lab4.straightLine(-10);

        lab4.rectangle(10,5);

        lab4.doArc(5.0, 360);
        lab4.doArc(5.0, -360);


        LCD.clear();
        LCD.drawString("Press any button", 0, 0);
        LCD.drawString("to exit.", 0, 1);
        while (true)
        {
            if (Button.ENTER.isDown() || Button.LEFT.isDown() || Button.RIGHT.isDown() | Button.ESCAPE.isDown())
            {
                LCD.clear();
                break;
            }
        }

    }
}
