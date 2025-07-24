package seungkyu;

import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapSqlRegistry implements UpdatableSqlRegistry {

    private final Map<String, String> sqlMap = new ConcurrentHashMap<>();

    @Override
    @SneakyThrows
    public void updateSql(String key, String sql) {
        if(sqlMap.get(key) == null)
            throw new RuntimeException();

        sqlMap.put(key, sql);
    }

    @Override
    public void updateSql(Map<String, String> sqlmap) {
        sqlmap.forEach(this::updateSql);
    }

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }

    @SneakyThrows
    @Override
    public String findSql(String key) {
        String sql = sqlMap.get(key);
        if(sql == null) throw new RuntimeException();
        return sql;
    }
}
