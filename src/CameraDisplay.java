import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CameraDisplay {
    private final JPanel displayContainer;
    private final JLabel[] labels = new JLabel[2];

    public CameraDisplay() {
        displayContainer = new JPanel();
        displayContainer.setBounds(0,0, 800, 400);

        labels[0] = this.addCameraLabel();
        labels[1] = this.addCameraLabel();
    }

    private JLabel addCameraLabel() {
        JLabel label = new JLabel();
        displayContainer.add(label);
        return label;
    }

    public void showImages(ImageIcon image, ImageIcon processed) {
        labels[0].setIcon(image);
        labels[1].setIcon(processed);
    }

    public JPanel getDisplayContainer() {
        return displayContainer;
    }
}

