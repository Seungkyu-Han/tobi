package seungkyu;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class SimpleSqlService implements SqlService {

    private final Map<String, String> sqlMap;

    @Override
    public String getSql(String key) {
        String sql = sqlMap.get(key);
        if (sql == null)
            throw new RuntimeException("sql not found: " + key);
        return sql;
    }
}
