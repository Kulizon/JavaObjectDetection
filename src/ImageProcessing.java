import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class ImageProcessing {
    public static Mat processImage(Mat frame) {
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

        Point center = findContoursCenter(contours);
        // draw point for debugging
        Imgproc.rectangle(processed,
                new Point(center.x + 5, center.y + 5),
                new Point(center.x + 15, center.y + 15),
                new Scalar(255, 0, 255), 15);

        return processed;
    }

    private static Point findContoursCenter(List<MatOfPoint> contours) {
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

        return new Point(approx[0], approx[1]);
    }
}
