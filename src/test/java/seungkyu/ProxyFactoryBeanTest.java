package seungkyu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
public class ProxyFactoryBeanTest {

    @Test
    public void pointCutAdvisor(){
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new HelloImpl());

        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedName("say*");

        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(nameMatchMethodPointcut, new DynamicProxyTest.UpperCaseAdvisor()));

        Hello proxyHello = (Hello) proxyFactoryBean.getObject();

        Assertions.assertEquals("HELLO SEUNGKYU", proxyHello.sayHello("seungkyu"));
        Assertions.assertEquals("HI SEUNGKYU", proxyHello.sayHi("seungkyu"));
        Assertions.assertEquals("BYE SEUNGKYU", proxyHello.sayBye("seungkyu"));
    }
}
