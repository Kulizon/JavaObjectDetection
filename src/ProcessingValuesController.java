import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.HashMap;
import java.util.Map;

public class ProcessingValuesController {
    private final JPanel slidersContainer;

    private final Map<String, Integer> sliders = new HashMap<String, Integer>();

    public ProcessingValuesController() {
        slidersContainer = new JPanel();
        slidersContainer.setLayout(new BoxLayout(slidersContainer, BoxLayout.Y_AXIS));
        slidersContainer.setSize(200, 400);

        slidersContainer.add(createSlider(109, "hueLow"));
        slidersContainer.add(createSlider(140, "hueHigh"));

        slidersContainer.add(createSlider(170, "satLow"));
        slidersContainer.add(createSlider(200, "satHigh"));

        slidersContainer.add(createSlider(70, "valLow"));
        slidersContainer.add(createSlider(240, "valHigh"));
    }

    private JSlider createSlider(int initVal, String name) {
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

        return slider;
    }

    public JPanel getSlidersContainer() {
        return slidersContainer;
    }

    public Map<String, Integer> getSliders() {
        return sliders;
    }
}
