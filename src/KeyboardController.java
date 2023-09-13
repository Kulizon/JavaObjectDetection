import org.opencv.core.Point;

public class KeyboardController {
    private final int MIN_WIDTH = 0;
    private final int MAX_WIDTH = 630;

    private final float MIN_HEIGHT = 0;
    private final float MAX_HEIGHT = 430;
    public KeyboardController() {

    }

    public void start() {
        while (true) {
            Point val = ImageProcessing.getCenter();

            if (val.x < MAX_WIDTH / 2 && val.y < MAX_HEIGHT / 2) {
                System.out.println("top left");
            } else if (val.x > MAX_WIDTH / 2 && val.y < MAX_HEIGHT / 2) {
                System.out.println("top right");
            } else if (val.x < MAX_WIDTH / 2 && val.y > MAX_HEIGHT / 2) {
                System.out.println("bottom left");
            } else if (val.x > MAX_WIDTH / 2 && val.y > MAX_HEIGHT / 2) {
                System.out.println("bottom right");
            }

        }
    }
}
