package seungkyu;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    @Test
    public void simpleProxy(){
        Hello proxyHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Hello.class},
                new HelloUpperCase(new seungkyu.HelloImpl())
        );
    }

    @Test
    public void proxyFactoryBean(){
        ProxyFactoryBean proxyBean = new ProxyFactoryBean();
        proxyBean.setTarget(new HelloImpl());
        proxyBean.addAdvice(new UpperCaseAdvisor());

        Hello proxy = (Hello) proxyBean.getObject();

        Assertions.assertEquals("HELLO SEUNGKYU", proxy.sayHello("seungkyu"));
    }

    static interface Hello {
        String sayHello(String name);
        String sayHi(String name);
        String sayBye(String name);
    }

    static class HelloImpl implements Hello {
        @Override
        public String sayHello(String name) {
            return "Hello " + name;
        }

        @Override
        public String sayHi(String name) {
            return "Hi " + name;
        }

        @Override
        public String sayBye(String name) {
            return "Bye " + name;
        }
    }

    static class UpperCaseAdvisor implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            if(invocation.getMethod().getReturnType().equals(String.class)){
                String returnValue = (String) invocation.proceed();
                if (returnValue != null) {
                    return returnValue.toUpperCase();
                }
                else
                    return null;
            }
            else
                return invocation.proceed();
        }
    }
}
