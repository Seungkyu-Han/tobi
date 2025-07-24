package seungkyu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Test
    @Transactional(readOnly = true)
    public void transactionSync(){

        userService.deleteAll();

        userService.add(User.builder()
                .id(new Date().toString()).build());
        userService.add(User.builder()
                .id(new Date().toString()).build());
    }

    @Test
    @Rollback
    @Transactional
    public void addUser(){
        userService.add(
                User.builder()
                        .id(UUID.randomUUID().toString().substring(0, 5))
                        .name("test")
                        .password("password")
                        .build()
        );
    }


}