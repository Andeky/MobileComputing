import java.awt.Robot;
import java.util.Random;
import java.awt.MouseInfo;

public class MouseMover {
    public static final int FIVE_SECONDS = 10;
    public static final int MAX_Y = 2;
    public static final int MAX_X = 2;
    public static final int upDown = 1;

    public static void main(String... args) throws Exception {
        Robot robot = new Robot();
        Random random = new Random();
        while (true) {
            int mouseX = (int)MouseInfo.getPointerInfo().getLocation().getX();
            int mouseY = (int)MouseInfo.getPointerInfo().getLocation().getY();

            System.out.print(mouseY + "\n");
            int moveX = random.nextInt(MAX_X) + 1;
            int moveY = random.nextInt(MAX_Y) + 1;

            int xCord = random.nextInt(upDown);
            int yCord = random.nextInt(upDown);

            if (xCord == 1) {
                moveX = -moveX;
            }
            //System.out.print(xCord);
            if (yCord == 1) {
                moveY = -moveY;
            }

            int newPositionX = moveX + mouseX;
            int newPositionY = moveY + mouseY;

            //robot.mouseMove(newPositionX, newPositionY);
            Thread.sleep(FIVE_SECONDS);
        }
    }
}
