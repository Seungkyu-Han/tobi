package seungkyu;

import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.concurrent.atomic.AtomicInteger;

public class EmbeddedDbTest {

    EmbeddedDatabase db;
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {

        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();

        jdbcTemplate = new JdbcTemplate(db);
    }

    @AfterEach
    public void tearDown() {
        db.shutdown();
    }

    @Test
    public void initData(){
        Assertions.assertEquals(2, jdbcTemplate.queryForObject("select count(*) from sqlmap", Integer.class));

        AtomicInteger index = new AtomicInteger();

        jdbcTemplate.queryForList("SELECT * FROM sqlmap").forEach(
                (mapEntry) -> {
                    Assertions.assertEquals("KEY" + (index.get() + 1), mapEntry.get("key_"));
                    Assertions.assertEquals("SQL" + (index.get() + 1), mapEntry.get("sql_"));
                    index.getAndIncrement();
                }
        );
    }

}
