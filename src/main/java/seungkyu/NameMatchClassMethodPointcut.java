package seungkyu;

import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.util.PatternMatchUtils;

public class NameMatchClassMethodPointcut extends NameMatchMethodPointcut {

    public NameMatchClassMethodPointcut(String mappedClassName) {
        this.setClassFilter(clazz -> PatternMatchUtils.simpleMatch(mappedClassName, clazz.getSimpleName()));
        this.addMethodName("**");
    }

}