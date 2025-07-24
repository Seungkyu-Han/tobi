package seungkyu;

import java.util.HashMap;
import java.util.Map;

public class MapSqlRegistry implements SqlRegistry{

    private final Map<String, String> sqlMap = new HashMap<>();

    @Override
    public String findSql(String key) {
        return sqlMap.getOrDefault(key, "NOT FOUND");
    }

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }
}
