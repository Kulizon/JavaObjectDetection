import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CameraDisplay {
    Camera camera;
    JFrame frame;
    JPanel displayContainer;
    JLabel[] labels = new JLabel[2];

    public CameraDisplay(JFrame frame, Camera camera) {
        this.frame = frame;
        this.camera = camera;

        displayContainer = new JPanel();
        displayContainer.setBounds(0,0, 800, 400);
        frame.add(displayContainer);

        labels[0] = this.addCameraLabel();
        labels[1] = this.addCameraLabel();

        frame.addWindowListener(
                new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {}
                    @Override
                    public void windowClosing(WindowEvent e) {
                        camera.release();
                    }
                    @Override
                    public void windowClosed(WindowEvent e) {}
                    @Override
                    public void windowIconified(WindowEvent e) {}
                    @Override
                    public void windowDeiconified(WindowEvent e) {}
                    @Override
                    public void windowActivated(WindowEvent e) {}
                    @Override
                    public void windowDeactivated(WindowEvent e) {}
                }
        );
    }

    private JLabel addCameraLabel() {
        JLabel label = new JLabel();
        displayContainer.add(label);
        return label;
    }

    public void showImages(ImageIcon image, ImageIcon processed) {
        labels[0].setIcon(image);
        labels[1].setIcon(processed);
        frame.repaint();
    }
}

