/**
 * Created by per-joelsompio on 17/11/16.
 */
public class Test2 implements TestClass {
    private MyKennel myKennel;

    public Test2() {
    }

    public void setUp() {
        myKennel = new MyKennel();
    }

    public void tearDown() {
        myKennel=null;
    }

    //Test that should fail
    public boolean testInitialisation() {
        return myKennel.value()==100;
    }

    //Test that should succeed
    public boolean testAddDog() {
        myKennel.increment();
        myKennel.increment();
        return myKennel.value()==2;

    }

    //Test that should succeed
    public boolean testSellDog() {
        myKennel.increment();
        myKennel.decrement();
        return myKennel.value() == 0;
    }

    //Test that should fail
    public boolean testFailingByException() {
        myKennel=null;
        myKennel.decrement();
        return true;

    }

    //Test that should fail
    public boolean testFailing() {
        return false;

    }
}