package seungkyu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAppContext {

    @Autowired UserDao userDao;

    @Bean
    public UserService testUserService() {
        return new TestUserServiceImpl(userDao, mailSender());
    }

    @Bean
    public MailSender mailSender() {
        return new MailSenderTest();
    }
}
