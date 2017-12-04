/**
 * Created by per-joelsompio on 17/11/16.
 */
public class MyKennel {

    private int val;
    public MyKennel() {
        val=0;
    }

    public void increment() {
        val=val + 1;
    }

    public void decrement() {
        val=val - 1;
    }

    public int value() {
        return val;
    }

}