/**
 * Created by per-joelsompio on 11/11/16.
 */
import javax.swing.SwingUtilities;

public class MyUnitTesterMain {

    public static void main(String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyUnitTesterFrame myFrame = new MyUnitTesterFrame("MyUnitTesterFrame");
            }
        });
    }
}
