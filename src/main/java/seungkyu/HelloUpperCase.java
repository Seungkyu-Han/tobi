package seungkyu;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class HelloUpperCase implements InvocationHandler {

    private final Hello target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().startsWith("say")) {
            String returnValue = (String)method.invoke(target, args);
            return returnValue.toUpperCase();
        }
        return method.invoke(target, args);
    }
}
