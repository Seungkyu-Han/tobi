package seungkyu;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitTest {

    static JUnitTest testObject;

    @Test
    public void test1(){
        System.out.println(this);
        System.out.println(testObject);
        Assertions.assertNotEquals(this, testObject);
        System.out.println(this);
        System.out.println(testObject);
        System.out.println();
    }

    @Test
    public void test2(){
        System.out.println(this);
        System.out.println(testObject);
        Assertions.assertNotEquals(this, testObject);
        testObject = this;
        System.out.println(this);
        System.out.println(testObject);
        System.out.println();
    }

    @Test
    public void test3(){
        System.out.println(this);
        System.out.println(testObject);
        Assertions.assertNotEquals(this, testObject);
        testObject = this;
        System.out.println(this);
        System.out.println(testObject);
        System.out.println();
    }
}
