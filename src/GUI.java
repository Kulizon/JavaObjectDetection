import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GUI {
    private final CameraDisplay display;
    private final ProcessingValuesController valuesController;
    JFrame frame;
    public GUI(Camera camera) {
        frame = new JFrame("Tracking");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setBounds(0, 0, 1600, 500);
        frame.setVisible(true);

        display = new CameraDisplay();
        valuesController = new ProcessingValuesController();

        frame.add(display.getDisplayContainer());
        frame.add(valuesController.getSlidersContainer());

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

    public CameraDisplay getDisplay() {
        return display;
    }
    public ProcessingValuesController getValuesController() {
        return valuesController;
    }

    public JFrame getFrame() {
        return frame;
    }
}
