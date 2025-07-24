package seungkyu;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Proxy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
public class TransactionHandlerTest {

    @Mock
    private UserDao userDao;

    @Mock
    private MailSender mailSender;

    @Mock
    private PlatformTransactionManager platformTransactionManager;

    @Test
    public void transactionMethod(){
        UserService proxyUser = (UserService) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{UserService.class},
                new TransactionHandler(
                        new UserServiceImpl(userDao, mailSender),
                        platformTransactionManager,
                        "tx"
                )
        );
    }
}
