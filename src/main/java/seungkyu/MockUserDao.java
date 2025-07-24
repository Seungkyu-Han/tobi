package seungkyu;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MockUserDao implements UserDao{

    private final List<User> users;

    @Getter
    private final List<User> updatedUsers = new ArrayList<>();

    public MockUserDao(List<User> users) {
        this.users = users;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public void update(User user) {
        updatedUsers.add(user);
    }
}
