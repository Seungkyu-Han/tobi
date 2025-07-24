package seungkyu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    private final SqlService sqlService;

    @Autowired
    public UserDaoImpl(
            DataSource dataSource,
            SqlService sqlService) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.sqlService = sqlService;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> User.builder()
            .id(rs.getString("id"))
            .name(rs.getString("name"))
            .password(rs.getString("password"))
            .login(rs.getInt("login"))
            .level(Level.from(rs.getInt("level")))
            .recommend(rs.getInt("recommend"))
            .email(rs.getString("email"))
            .build();

    public void add(User user){
        this.jdbcTemplate.update(
                this.sqlService.getSql("userAdd"),
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getLevel().getValue(),
                user.getLogin(),
                user.getRecommend()
        );
    }

    public User get(String id){
        return jdbcTemplate.queryForObject(
                this.sqlService.getSql("userGet"), userRowMapper, id);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query(
                this.sqlService.getSql("userGetAll"), userRowMapper);
    }

    public void update(User user) {
        this.jdbcTemplate.update(
                this.sqlService.getSql("userUpdate"),
                user.getName(),
                user.getPassword(),
                user.getLevel().getValue(),
                user.getLogin(),
                user.getRecommend(),
                user.getEmail(),
                user.getId()
        );
    }

    @Transactional
    public void deleteAll(){
        this.jdbcTemplate.update(this.sqlService.getSql("userDeleteAll"));
    }

    public int getCount() {
        Integer result = jdbcTemplate.queryForObject(this.sqlService.getSql("userGetCount"), Integer.class);
        return result == null ? 0 : result;
    }
}
