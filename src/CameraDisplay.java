import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CameraDisplay {
    Camera camera;
    JFrame frame;
    JLabel cameraLabel;
    public CameraDisplay() {

    }

    public void start() {
        frame = new JFrame("Label Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

        cameraLabel = new JLabel();
        cameraLabel.setBounds(10, 10, 400, 400);
        cameraLabel.setSize(200, 200);
        frame.add(cameraLabel);

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
    public void showImage(ImageIcon image) {
        cameraLabel.setIcon(image);
    }
}

