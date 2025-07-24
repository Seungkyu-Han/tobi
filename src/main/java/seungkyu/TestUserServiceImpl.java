package seungkyu;

import java.util.List;
import java.util.UUID;

public class TestUserServiceImpl extends UserServiceImpl {

    public TestUserServiceImpl(UserDao userDao, MailSender mailSender) {
        super(userDao, mailSender);
    }

    @Override
    public List<User> getAll() {
        super.add(
                User.builder()
                        .id(UUID.randomUUID().toString())
                        .build()
        );
        return super.getAll();
    }
}