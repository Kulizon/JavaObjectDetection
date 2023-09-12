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
import java.util.Random;

public class Camera {
    public VideoCapture capture = new VideoCapture(0);
    public CameraDisplay display;
    public Camera(CameraDisplay display) {
        this.display = display;
    }
    private double[] lastApprox = new double[2];

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
        final Mat processed = frame.clone();

        // detect only blue
        Imgproc.cvtColor(frame, processed, Imgproc.COLOR_BGR2HSV);
        Scalar low = new Scalar(109,170,70);
        Scalar high = new Scalar(140,200,240);
        Core.inRange(processed, low, high, processed); //hsv

        // detect objects
        Imgproc.GaussianBlur(processed, processed, new Size(7, 7), 2);
        Imgproc.morphologyEx(processed, processed, Imgproc.MORPH_CLOSE, Mat.ones(5,5, CvType.CV_64F));
        Imgproc.Canny(processed, processed, 150, 55);
        Imgproc.dilate(processed, processed, new Mat(), new Point(-1, -1), 1);

        // find contour points
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(processed, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        double[] approx = new double[2];
        approx[0] = 0;
        approx[1] = 0;
        for (MatOfPoint cnt : contours) {
            Rect rect = Imgproc.boundingRect(cnt);
            approx[0] += rect.x;
            approx[1] += rect.y;
        }


        approx[0] /= contours.size();
        approx[1] /= contours.size();

        Point center = Math.abs(lastApprox[0] - approx[0] + lastApprox[1] - approx[1]) > 50 ? new Point(lastApprox[0], lastApprox[1]) : new Point(approx[0], approx[1]);

        lastApprox[0] = approx[0];
        lastApprox[1] = approx[1];

        Imgproc.rectangle(processed,
                new Point(center.x + 5, center.y + 5),
                new Point(center.x + 15, center.y + 15),
                new Scalar(255, 0, 255), 15);

        return processed;
    }

    public void release() {
        this.capture.release();
    }
}
