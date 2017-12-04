/**
 * Created by per-joelsompio on 14/11/16.
 */
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.String;

public class UnitTester {
    private Method[] testClassMethods;
    private Class<?> testClass;
    private Object classInstance;
    private Class<?>[] testClassInterfaces;
    public ArrayList<String> testResults;
    private boolean ifSetUp = false;
    private boolean ifTearDown = false;

    UnitTester(String className) throws ClassNotFoundException {
        testClass = Class.forName(className);
        testClassMethods = testClass.getMethods();
        testClassInterfaces = testClass.getInterfaces();
    }

    public boolean runTest() {
        try {
            classInstance = testClass.newInstance();
        } catch (Exception e) {
            System.out.println("Could not instantiate class to be tested.");
            System.out.println(e.getCause().toString());
        }

        //saving testresults in an arraylist
        testResults = new ArrayList<String>();

        for (Method method : testClassMethods) {
            if(method.getName().startsWith("test")
                    && method.getReturnType() == boolean.class) {
                if(!ifSetUp) {
                    this.setUp();
                }

                try {
                    if((boolean) method.invoke(classInstance)) {
                        testResults.add("Success: " + method.getName());
                    }
                    else {
                        testResults.add("Failed: " + method.getName());
                    }
                }
                catch(Exception e) {
                    testResults.add("Failed: " + method.getName() + " " + e.getCause().toString());
                    System.out.println("Failed: Invoke class" + method.getName());
                    System.out.println(e.getCause().toString());
                    System.out.println("\n");
                }
                if(!ifTearDown) {
                    this.tearDown();
                }
            }
        }
        return true;
    }

    public void setUp() {
        try{
            testClass.getMethod("setUp").invoke(classInstance);

        }
        catch(Exception e) {
            System.out.println("Failed to set up");
            System.out.println(e.getCause().toString());
        }
    }

    public void tearDown() {
        try {
            testClass.getMethod("tearDown").invoke(classInstance);
        }
        catch(Exception e) {
            System.out.println("Failed to tear down");
            System.out.println(e.getCause().toString());
        }
    }

    public ArrayList<String> getArrayList() {
        return testResults;
    }
}