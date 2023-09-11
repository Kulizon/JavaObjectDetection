import org.opencv.core.Core;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        CameraDisplay display = new CameraDisplay();
        Camera camera = new Camera(display);
        display.setCamera(camera);

        display.start();


        new Thread(new Runnable() {
            @Override public void run()
            {
                try {
                    camera.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


    }
}