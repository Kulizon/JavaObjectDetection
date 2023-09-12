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

    private JLabel addCameraLabel() {
        JLabel label = new JLabel();
        displayContainer.add(label);
        return label;
    }

    public void start() {
        frame = new JFrame("Label Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setBounds(0, 0, 1300, 500);
        frame.setVisible(true);

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
    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    public void showImages(ImageIcon image, ImageIcon processed) {
        labels[0].setIcon(image);
        labels[1].setIcon(processed);
        frame.repaint();
    }
}

