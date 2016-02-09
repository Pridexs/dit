import lejos.nxt.LCD;
import lejos.nxt.Button;
import javax.microedition.lcdui.Graphics;

public class Lab2 {
  public static void main (String[] args) throws Exception {

    // Step 1
    LCD.drawString("Free RAM:", 0, 0);
    LCD.drawInt((int) System.getRuntime().freeMemory(), 6, 9, 0);
    Thread.sleep(5000);
    LCD.clear();

    Graphics g = new Graphics();
    g.drawLine(0,0,30,30);
    g.drawRect(32, 32, 30, 25);
    Thread.sleep(5000);
    LCD.clear();

    // Step 2
    /*Graphics g = new Graphics();
    g.drawLine(0,0,30,30);
    g.drawRect(32, 32, 30, 25);
    Thread.sleep(5000);
    LCD.clear();*/

    // Step 3
    LCD.drawString("Press LEFT, ", 0, 0);
    LCD.drawString("RIGHT or ESCAPE", 0, 1);
    while (true)
    {
      if (Button.ESCAPE.isDown())
      {
        LCD.clear();
        LCD.drawString("Exiting...", 0, 0);
        break;
      }
      if (Button.LEFT.isDown()) {
        LCD.clear();
        LCD.drawString("LEFT", 0, 0);
      }
      if (Button.RIGHT.isDown()) {
        LCD.clear();
        LCD.drawString("RIGHT", 0, 0);
      }
    }
    Thread.sleep(5000);

    // Step 4
    LCD.clear();
    LCD.drawString("Waiting for", 0, 0);
    LCD.drawString("ENTER", 0, 1);
    while (true)
    {
      if (Button.ENTER.isDown())
      {
        LCD.clear();
        LCD.drawString("Finished", 0, 0);
        break;
      }
    }
    Thread.sleep(2000);
  }
}
