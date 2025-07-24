package seungkyu;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class PointcutExpressionTest {

    @Test
    public void methodSignaturePointcut() throws SecurityException, NoSuchMethodException {
        targetClassPointMatches("execution(* *(..))", true, true, true, true, true);
    }

    public void targetClassPointMatches(String expression, boolean... expected) {
        pointcutMatches(expression, expected[0], Target.class, "hello");
        pointcutMatches(expression, expected[1], Target.class, "hello", String.class);
        pointcutMatches(expression, expected[2], Target.class, "plus", int.class, int.class);
        pointcutMatches(expression, expected[3], Target.class, "minus", int.class, int.class);
        pointcutMatches(expression, expected[4], Target.class, "method");
    }

    @SneakyThrows
    public void pointcutMatches(String expression, Boolean expected, Class<?> clazz, String methodName, Class<?> ... args){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);

        Assertions.assertTrue(pointcut.getClassFilter().matches(clazz));
        Assertions.assertTrue(pointcut.getMethodMatcher().matches(clazz.getMethod(methodName, args), null));
    }
}
