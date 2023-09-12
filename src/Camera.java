import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Size;
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

            Mat processed = this.processImage(frame);

            Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2BGRA);

            display.showImages(new ImageIcon(createBufferedImage(frame)), new ImageIcon(createBufferedImage(processed)));
        }
    }

    private BufferedImage createBufferedImage(Mat mat) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", mat, buffer);
        try {
            return ImageIO.read(new ByteArrayInputStream(buffer.toArray()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Mat processImage(Mat frame) {
        final Mat processed = new Mat(frame.height(), frame.width(), frame.type());

        Imgproc.GaussianBlur(frame, processed, new Size(7, 7), 1);
        Imgproc.cvtColor(processed, processed, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(processed, processed, 200, 25);
        Imgproc.dilate(processed, processed, new Mat(), new Point(-1, -1), 1);

        return processed;
    }

    public void release() {
        this.capture.release();
    }
}
