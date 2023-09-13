import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Point;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Camera {
    public VideoCapture capture = new VideoCapture(0);


    public void start(GUI gui) throws IOException {

        while(capture.isOpened()) {
            Mat frame = new Mat();
            capture.read(frame);
            Core.flip(frame, frame, 1); // 1 means left to right

            Mat processed = ImageProcessing.processImage(frame, gui.getValuesController().getSliders());
            gui.getDisplay().showImages(new ImageIcon(createBufferedImage(frame)), new ImageIcon(createBufferedImage(processed)));
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



    public void release() {
        this.capture.release();
    }
}
