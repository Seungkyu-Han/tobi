package seungkyu;

import jakarta.annotation.Nonnull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class HelloTest {

    @Test
    public void classNamePointCutAdvisor(){
        NameMatchMethodPointcut classMethodPointCut = new NameMatchMethodPointcut(){
            @Override
            @Nonnull
            //클래스 필터를 재정의
            public ClassFilter getClassFilter() {
                //해당 이름으로 시작하는 클래스만 적용
                return clazz -> clazz.getSimpleName().startsWith("Hi");
            }
        };

        //해당 클래스에서도 해당 메서드에만 프록시를 적용
        classMethodPointCut.setMappedName("say*");

        class HiChild extends DynamicProxyTest.HelloImpl{}
        checkAdvice(new HiChild(), classMethodPointCut, true);

        class HelloChild extends DynamicProxyTest.HelloImpl{}
        checkAdvice(new HelloChild(), classMethodPointCut, false);
    }

    private void checkAdvice(Object target, Pointcut pointcut, boolean isAdvice){
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(target);
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new DynamicProxyTest.UpperCaseAdvisor()));
        DynamicProxyTest.Hello proxyHello = (DynamicProxyTest.Hello) proxyFactoryBean.getObject();

        if(isAdvice){
            Assertions.assertEquals("HELLO SEUNGKYU", proxyHello.sayHello("seungkyu"));
            Assertions.assertEquals("HI SEUNGKYU", proxyHello.sayHi("seungkyu"));
            Assertions.assertEquals("BYE SEUNGKYU", proxyHello.sayBye("seungkyu"));
        }
        else{
            Assertions.assertEquals("Hello seungkyu", proxyHello.sayHello("seungkyu"));
            Assertions.assertEquals("Hi seungkyu", proxyHello.sayHi("seungkyu"));
            Assertions.assertEquals("Bye seungkyu", proxyHello.sayBye("seungkyu"));
        }
    }
}