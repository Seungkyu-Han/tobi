package seungkyu;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    private final MailSender mailSender;

    public void upgradeLevels() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (user.isUpgradeLevel()) upgradeLevel(user);
        }
    }

    public void add(User user) {
        if(user.getLevel() == null)
            user.setLevel(Level.BRONZE);
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    public User get(String id) {
        return userDao.get(id);
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userDao.getAll();
    }

    public void deleteAll() {
        userDao.deleteAll();
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
        mailSender.sendUpgradeEmail(user);
    }
}