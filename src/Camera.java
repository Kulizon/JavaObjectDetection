import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Camera {
    public VideoCapture capture = new VideoCapture(0);
    public CameraDisplay display;

    public Camera(CameraDisplay display) {
        this.display = display;
    }

    public void start() throws IOException {

        while(capture.isOpened()) {
            Mat frame = new Mat();
            capture.read(frame);
            Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2BGRA);

            MatOfByte buffer = new MatOfByte();
            Imgcodecs.imencode(".png", frame, buffer);
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(buffer.toArray()));

//            BufferedImage bufferedImageFiltered = bufferedImage.setRGB();

            display.showImage(new ImageIcon(bufferedImage));

        }
    }

    public void release() {
        this.capture.release();
    }
}
