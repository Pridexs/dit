import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.Delay;
import lejos.nxt.NXTMotor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.util.Stopwatch;

public class Lab4 {
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

    
    
  }
}
