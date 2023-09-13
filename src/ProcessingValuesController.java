import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ProcessingValuesController {
    private final JPanel slidersContainer;

    private final Map<String, Integer> sliders = new HashMap<String, Integer>();

    public ProcessingValuesController() {
        slidersContainer = new JPanel();
        slidersContainer.setLayout(new BoxLayout(slidersContainer, BoxLayout.Y_AXIS));
        slidersContainer.setSize(200, 400);

        slidersContainer.add(createSlider(110, "hueLow"));
        slidersContainer.add(createSlider(140, "hueHigh"));

        slidersContainer.add(createSlider(205, "satLow"));
        slidersContainer.add(createSlider(250, "satHigh"));

        slidersContainer.add(createSlider(70, "valLow"));
        slidersContainer.add(createSlider(240, "valHigh"));
    }

    private JPanel createSlider(int initVal, String name) {
        final int MIN_VAL = 0;
        final int MAX_VAL = 255;

        JSlider slider = new JSlider(JSlider.HORIZONTAL, MIN_VAL, MAX_VAL, initVal);
        slider.setMinorTickSpacing(1);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                slider.setValue(source.getValue());
                sliders.put(name, source.getValue());
            }
        });

        sliders.put(name, initVal);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(name, JLabel.LEFT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        container.add(label);
        container.add(slider);

        return container;
    }

    public JPanel getSlidersContainer() {
        return slidersContainer;
    }

    public Map<String, Integer> getSliders() {
        return sliders;
    }
}
