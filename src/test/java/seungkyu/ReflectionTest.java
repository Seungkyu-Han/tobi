package seungkyu;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class ReflectionTest {

    @Test
    @SneakyThrows
    public void invokeMethodTest(){
        String name = "seungkyu";
        int length = name.length();

        Method lengthMethod = String.class.getMethod("length");
        Assertions.assertEquals(length, lengthMethod.invoke(name));

        Method concatmethod = String.class.getMethod("concat", String.class);
        Assertions.assertEquals(name + name, concatmethod.invoke(name, name));

    }
}
