package seungkyu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EmbeddedDbSqlRegistryTest extends AbstractUpdatableSqlRegistryTest{

    EmbeddedDatabase db;

    @Override
    protected UpdatableSqlRegistry injectSqlRegistry() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("/schema.sql")
                .build();

        return new EmbeddedDbSqlRegistry(db);
    }

    @AfterEach
    public void tearDown() {
        db.shutdown();
    }

    @Test
    public void transactionTest(){
        Map<String, String> sqlmap = new HashMap<>();
        sqlmap.put("KEY1", "U_SQL1");
        sqlmap.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        try
        {
            sqlRegistry.updateSql(sqlmap);
            Assertions.fail();
        }
        catch (RuntimeException e)
        {
            Assertions.assertTrue(
                    sqlRegistry.findSql("KEY1").equals("SQL1") &&
                            sqlRegistry.findSql("KEY2").equals("SQL2") &&
                            sqlRegistry.findSql("KEY3").equals("SQL3")
            );
        }
    }
}