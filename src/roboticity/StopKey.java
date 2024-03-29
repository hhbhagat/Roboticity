package roboticity;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class StopKey implements NativeKeyListener {
        public void nativeKeyPressed(NativeKeyEvent e) {
                System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

                if (e.getKeyCode() == NativeKeyEvent.VK_NUM_LOCK) {
                    System.out.println("Stopping.");
                        //GlobalScreen.unregisterNativeHook();
                }
        }

        public void nativeKeyReleased(NativeKeyEvent e) {
                System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        }

        public void nativeKeyTyped(NativeKeyEvent e) {
                System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
        }

        public void listen() {
                try {
                        GlobalScreen.registerNativeHook();
                }
                catch (NativeHookException ex) {
                        System.err.println("There was a problem registering the native hook.");
                        System.err.println(ex.getMessage());
                }

                //Construct the example object and initialze native hook.
                GlobalScreen.getInstance().addNativeKeyListener(new StopKey());
        }
}