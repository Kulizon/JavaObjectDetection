import javax.swing.*;
import java.awt.*;

public class GUI {
    private JFrame frame;
    private CameraDisplay display;

    public GUI(Camera camera) {
        frame = new JFrame("Tracking");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setBounds(0, 0, 1300, 500);
        frame.setVisible(true);

        display = new CameraDisplay(frame, camera);
    }

    public CameraDisplay getDisplay() {
        return display;
    }
}
