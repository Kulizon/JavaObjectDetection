import org.opencv.core.Core;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Camera camera = new Camera();
        GUI gui = new GUI(camera);


        new Thread(new Runnable() {
            @Override public void run()
            {
                try {
                    camera.start(gui);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


    }
}