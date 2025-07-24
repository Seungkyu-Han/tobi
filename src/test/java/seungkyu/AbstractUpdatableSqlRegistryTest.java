package seungkyu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractUpdatableSqlRegistryTest {

    UpdatableSqlRegistry sqlRegistry;

    abstract protected UpdatableSqlRegistry injectSqlRegistry();

    @BeforeEach
    public void setUp() {
        sqlRegistry = injectSqlRegistry();

        sqlRegistry.registerSql("KEY1", "SQL1");
        sqlRegistry.registerSql("KEY2", "SQL2");
        sqlRegistry.registerSql("KEY3", "SQL3");
    }


    @Test
    public void find(){
        Assertions.assertTrue(
                sqlRegistry.findSql("KEY1").equals("SQL1") &&
                        sqlRegistry.findSql("KEY2").equals("SQL2") &&
                        sqlRegistry.findSql("KEY3").equals("SQL3")
        );
    }

    @Test
    public void notFoundKey(){
        Assertions.assertThrows(RuntimeException.class, () -> sqlRegistry.findSql(UUID.randomUUID().toString()));
    }

    @Test
    public void updateSql(){
        sqlRegistry.updateSql("KEY1", "U_SQL1");
        Assertions.assertTrue(
                sqlRegistry.findSql("KEY1").equals("U_SQL1") &&
                        sqlRegistry.findSql("KEY2").equals("SQL2") &&
                        sqlRegistry.findSql("KEY3").equals("SQL3")
        );
    }

    @Test
    public void updateMulti(){
        Map<String, String> sqlmap = new HashMap<>();

        sqlmap.put("KEY1", "U_SQL1");
        sqlmap.put("KEY3", "U_SQL3");

        sqlRegistry.updateSql(sqlmap);
        Assertions.assertTrue(
                sqlRegistry.findSql("KEY1").equals("U_SQL1") &&
                        sqlRegistry.findSql("KEY2").equals("SQL2") &&
                        sqlRegistry.findSql("KEY3").equals("U_SQL3")
        );
    }
}
