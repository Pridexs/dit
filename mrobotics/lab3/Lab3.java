import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.Delay;
import lejos.nxt.NXTMotor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.util.Stopwatch;

public class Lab3 {
  public static void main (String[] args) throws Exception {
    // Step 1
    LCD.clear();
    LCD.drawString("Waiting for", 0, 0);
    LCD.drawString("Button", 0, 1);

    while (true)
    {
      if (Button.ENTER.isDown() || Button.LEFT.isDown() || Button.RIGHT.isDown())
      {
        LCD.clear();
        break;
      }
    }

    /*
     * Normal Setting with Speed Regulation
     */
    /*
    Motor.A.setSpeed(720);
    Motor.B.setSpeed(720);

    Motor.A.forward();
    Motor.B.forward();
    for (int i = 0; i < 8; i++)
    {
        Delay.msDelay(1000);
        LCD.clear();
        LCD.drawInt(i+1,0,0);
        LCD.drawInt(Motor.A.getTachoCount(),2,0);
        LCD.drawInt(Motor.B.getTachoCount(),7,0);
    }
    Motor.A.stop();
    Motor.B.stop();
    LCD.clear();
    */

    /*
     * Suspend Regulation
     * Low Level Functions are needed
     * if .forward() is called, regulation is automatically enabled.
    */
    /*
    NXTMotor m1 = new NXTMotor(MotorPort.A);
    NXTMotor m2 = new NXTMotor(MotorPort.B);

    m1.setPower(90);
    m2.setPower(90);
    for (int i = 0; i < 8; i++)
    {
        Delay.msDelay(1000);
        LCD.clear();
        LCD.drawInt(i+1,0,0);
        LCD.drawInt(m1.getTachoCount(),2,0);
        LCD.drawInt(m2.getTachoCount(),7,0);
    }
    m1.stop();
    m2.stop();
    */

    Motor.A.setSpeed(720);
    Motor.B.setSpeed(720);

    Motor.A.forward();
    Motor.B.forward();

    Stopwatch clock = new Stopwatch();
    clock.reset();
    int count = 0;
    while(!Button.ESCAPE.isDown())
    {
        if (clock.elapsed() > 1000)
        {
            LCD.drawInt(++count,0,0);
            LCD.drawInt(Motor.A.getTachoCount(),0,1);
            LCD.drawInt(Motor.B.getTachoCount(),0,2);
            clock.reset();
        }
    }
    Motor.A.stop();
    Motor.B.stop();
    LCD.clear();


  }
}
