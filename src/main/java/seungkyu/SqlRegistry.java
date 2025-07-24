package seungkyu;

import org.springframework.data.crossstore.ChangeSetPersister;

public interface SqlRegistry {

    void registerSql(String key, String sql);

    String findSql(String key);
}
