package seungkyu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(UUID.randomUUID().toString().substring(0, 8))
                .name("seungkyu")
                .password("123456")
                .login(1)
                .recommend(2)
                .level(Level.BRONZE)
                .build();
    }

    @Test
    public void upgradeLevelTest(){
        user.upgradeLevel();

        Assertions.assertEquals(Level.SILVER, user.getLevel());
    }
}