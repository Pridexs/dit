import lejos.nxt.*;

/*
public class WaitForClap implements Behavior {
    private boolean suppressed = false;

    public boolean takeControl() {
        return true;
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {

        while( !suppressed ) {
            Thread.yield();
        }
    }
}
*/

public class Assignment1 {
    public static void main (String[] args) throws Exception {
        Thread.sleep(100);

        /* PART 1 - Checking for clapping
        SoundSensor sound = new SoundSensor(SensorPort.S1);
        LCD.drawString("Sound test!", 0, 0);
        while (true) {
            if (Button.ENTER.isDown() || Button.LEFT.isDown() || Button.RIGHT.isDown()) {
                LCD.clear();
                break;
            }
        }

        while (!Button.ESCAPE.isDown() && sound.readValue() < 40) {
            LCD.drawString(Integer.toString(sound.readValue()), 0, 0);
            Thread.sleep(20);
        }

        LCD.drawString(Integer.toString(sound.readValue()), 0, 0);
        while (true) {
            if (Button.ENTER.isDown() || Button.LEFT.isDown() || Button.RIGHT.isDown()) {
                LCD.clear();
                break;
            }
        }
        */

        /* PART 2 - Moving forward until hits darker area
        LightSensor light = new LightSensor(SensorPort.S2);
        //Motor.A.setSpeed(400);
        //Motor.B.setSpeed(400);
        //Motor.A.forward();
        //Motor.B.forward();
        while (!Button.ESCAPE.isDown() && light.getNormalizedLightValue() > 400) {
            Thread.sleep(20);
        }
        //Motor.A.stop();
        //Motor.B.stop();
        */

        /* PART 3 - Sonic
        */
        UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S3);

        while (!Button.ESCAPE.isDown()) {
          LCD.clear();
          // 5 cm de erro
          LCD.drawInt(sonic.getDistance(), 0, 3);
          Thread.sleep(500);
        }
    }
}
