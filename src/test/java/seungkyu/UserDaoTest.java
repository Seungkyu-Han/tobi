package seungkyu;

import lombok.Setter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppContext.class, TestAppContext.class})
@Setter
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        System.out.println(userService.getClass());
        userDao.deleteAll();
    }

    @Test
    public void addAndGet(){
        Assertions.assertEquals(0, userDao.getCount());

        User createdUser = User.builder()
                .id("seungkyu")
                .name("승규")
                .password("password")
                .level(Level.BRONZE)
                .recommend(1)
                .login(10)
                .build();

        userDao.add(createdUser);
        Assertions.assertEquals(1, userDao.getCount());

        User readUser = userDao.get("seungkyu");

        Assertions.assertEquals(createdUser, readUser);
    }

    @Test
    public void count(){

        for(int i = 0; i < 3; i++){

            User user = User.builder()
                    .id(UUID.randomUUID().toString().substring(0, 6))
                    .name(UUID.randomUUID().toString().substring(0, 6))
                    .password(UUID.randomUUID().toString().substring(0, 6))
                    .level(Level.BRONZE)
                    .recommend(i)
                    .login(i)
                    .build();

            userDao.add(user);

            Assertions.assertEquals(i + 1, userDao.getCount());
        }
    }

    @Test
    public void countWithDuplicate() {
        String name = UUID.randomUUID().toString().substring(0, 6);
        String id = UUID.randomUUID().toString().substring(0, 6);

        User user = User.builder()
                .name(name)
                .id(id)
                .password(UUID.randomUUID().toString().substring(0, 6))
                .level(Level.BRONZE)
                .recommend(1)
                .login(1)
                .build();

        userDao.add(user);

        for(int i = 0; i < 2; i++){

            user.setId(id);
            user.setName(UUID.randomUUID().toString().substring(0, 6));
            user.setPassword(UUID.randomUUID().toString().substring(0, 6));
            user.setLevel(Level.BRONZE);
            user.setRecommend(i);
            user.setLogin(i);

            Assertions.assertThrows(DuplicateKeyException.class,
                    () -> userDao.add(user));

            Assertions.assertEquals(1, userDao.getCount());
        }
    }

    @Test
    public void get() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> userDao.get(UUID.randomUUID().toString().substring(0, 6)));
    }

    @Test
    public void getAll() {
        User user1 = User.builder()
                .id("user1")
                .name("user1")
                .password("pw1")
                .level(Level.BRONZE)
                .recommend(1)
                .login(1)
                .build();

        User user2 = User.builder()
                .id("user2")
                .name("user2")
                .password("pw2")
                .level(Level.BRONZE)
                .recommend(1)
                .login(1)
                .build();

        userDao.deleteAll();

        userDao.add(user1);
        List<User> users1 = userDao.getAll();
        Assertions.assertEquals(1, users1.size());
        Assertions.assertEquals(user1, users1.get(0));

        userDao.add(user2);
        List<User> users2 = userDao.getAll();
        Assertions.assertEquals(2, users2.size());
        Assertions.assertEquals(user1, users2.get(0));
        Assertions.assertEquals(user2, users2.get(1));
    }

    @Test
    public void getAllWithEmpty() {
        userDao.deleteAll();

        List<User> users = userDao.getAll();
        Assertions.assertEquals(0, users.size());
    }
    @Test
    public void update() {
        userDao.deleteAll();

        var originalName = UUID.randomUUID().toString().substring(0, 6);
        var newName = UUID.randomUUID().toString().substring(0, 6);
        var originalLevel = Level.BRONZE;
        var newLevel = Level.GOLD;

        User modifyUser = User.builder()
                .id("seungkyu")
                .name(originalName)
                .password("password")
                .level(originalLevel)
                .recommend(1)
                .login(1)
                .build();

        User unmodifyUser = User.builder()
                .id("seungkyu1")
                .name(originalName)
                .password("password")
                .level(originalLevel)
                .recommend(1)
                .login(1)
                .build();

        userDao.add(modifyUser);
        userDao.add(unmodifyUser);

        // update
        modifyUser.setName(newName);
        modifyUser.setLevel(newLevel);
        userDao.update(modifyUser);

        // assertions
        User updated = userDao.get("seungkyu");
        Assertions.assertEquals(newName, updated.getName());

        User untouched = userDao.get("seungkyu1");
        Assertions.assertEquals(originalName, untouched.getName());
    }

}