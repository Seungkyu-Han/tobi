package seungkyu;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class TransactionHandler implements InvocationHandler {

    private final Object target;
    private final PlatformTransactionManager platformTransactionManager;
    private final String pattern;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().startsWith(pattern)) {
            return this.invocationTransaction(method, args);
        }
        else{
            return method.invoke(target, args);
        }
    }

    private Object invocationTransaction(Method method, Object[] args) throws Throwable {
        TransactionStatus status = this.platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        try
        {
            Object returnValue = method.invoke(target, args);
            this.platformTransactionManager.commit(status);
            return returnValue;
        }
        catch(InvocationTargetException e)
        {
            this.platformTransactionManager.rollback(status);
            throw e.getTargetException();
        }
    }
}