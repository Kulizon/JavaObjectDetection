import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.NativeInputEvent;

import org.opencv.core.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardController {
    private final int MIN_WIDTH = 0;
    private final int MAX_WIDTH = 630;
    private final float MIN_HEIGHT = 0;
    private final float MAX_HEIGHT = 430;
    private boolean isRunning = false;
    private final Robot r;
    public KeyboardController() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("Failed to register native hook: " + ex.getMessage());
        }

        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent e) {
                System.out.println("Key Pressed: " + e.getKeyCode());
                if (e.getKeyCode() == NativeKeyEvent.VC_Q) {
                    isRunning = !isRunning;
                    System.out.println("==============================================================================");
                }
            }
        });
    }
    public void start() {
        while (true) {
            Point val = ImageProcessing.getCenter();

            System.out.println(isRunning );

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!isRunning || Double.isNaN(val.x) || val.x == 0f) continue;

            int section = MAX_WIDTH / 5;

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (val.x < section ) {
               simulatePress(KeyEvent.VK_A);
            } else if (val.x < section * 2) {
               simulatePress(KeyEvent.VK_W);
               simulatePress(KeyEvent.VK_A);
            } else if (val.x < section * 3) {
                simulatePress(KeyEvent.VK_W);;
            } else if (val.x < section * 4) {
                simulatePress(KeyEvent.VK_W);
                simulatePress(KeyEvent.VK_D);
            } else if (val.x < section * 5) {
                simulatePress(KeyEvent.VK_D);
            }

        }
    }

    private void simulatePress(int ev) {
        r.keyPress(ev);
        r.keyRelease(ev);
    }
}
