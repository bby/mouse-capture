import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import java.io.*;

public class GlobalMouseListenerExample implements NativeMouseInputListener {

        private int prev_x;
        private int prev_y;
        private int distance_moved_x;
        private int distance_moved_y;
        private int[] xArray;
        private int[] yArray;
        private int count = 0;

        public static void main(String[] args) {
                try {
                        GlobalScreen.registerNativeHook();
                }
                catch (NativeHookException ex) {
                        System.err.println("There was a problem registering the native hook.");
                        System.err.println(ex.getMessage());

                        System.exit(1);
                }

                //Construct the example object.
                GlobalMouseListenerExample example = new GlobalMouseListenerExample();

                //Add the appropriate listeners for the example object.
                GlobalScreen.getInstance().addNativeMouseListener(example);
                GlobalScreen.getInstance().addNativeMouseMotionListener(example);
        }

        public GlobalMouseListenerExample() {
                xArray = new int[5000];
                yArray = new int[5000];
        }

        public void nativeMouseClicked(NativeMouseEvent e) {
                //System.out.println("Mosue Clicked: " + e.getClickCount());
        }

        public void nativeMousePressed(NativeMouseEvent e) {
                //System.out.println("Mosue Pressed: " + e.getButton());
        }

        public void nativeMouseReleased(NativeMouseEvent e) {
                //System.out.println("Mosue Released: " + e.getButton());
        }

        public void nativeMouseMoved(NativeMouseEvent e) {    
                distanceMoved(e);
        }

        public void nativeMouseDragged(NativeMouseEvent e) {
                distanceMoved(e);
        }

        public void distanceMoved(NativeMouseEvent e) {
                distance_moved_x = prev_x - e.getX();
                distance_moved_y = prev_y - e.getY();

                prev_x = e.getX();
                prev_y = e.getY();

                if(count<=4999) {
                        if(count!=0) {
                            xArray[count] = Math.abs(distance_moved_x);
                            yArray[count] = Math.abs(distance_moved_y);
                        }
                } 
                else {
                        int sumX = 0;
                        for (int i : xArray) {
                            sumX += i;
                        }

                        int sumY = 0;
                        for (int i : yArray) {
                            sumY += i;
                        }

                        try {
                            PrintWriter out = new PrintWriter(new FileWriter("distances.txt", true)); 
                            String newLine = System.getProperty("line.separator");
                            out.println(sumX+","+sumY); 
                            out.close();
                        } catch(IOException e1) {
                            System.out.println("Error during reading/writing");
                        }

                        count = 0;
                }
                count++;
        }
}