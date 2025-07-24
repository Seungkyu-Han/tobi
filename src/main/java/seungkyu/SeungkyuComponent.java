package seungkyu;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface SeungkyuComponent {
}
