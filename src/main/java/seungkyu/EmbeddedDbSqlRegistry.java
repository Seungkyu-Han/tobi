package seungkyu;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Map;

public class EmbeddedDbSqlRegistry implements UpdatableSqlRegistry {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public EmbeddedDbSqlRegistry(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
    }

    @Override
    public void registerSql(String key, String sql) {
        jdbcTemplate.update("INSERT INTO sqlmap (key_, sql_) values (?, ?)", key, sql);
    }

    @Override
    public String findSql(String key) {
        try
        {
            return jdbcTemplate.queryForObject("SELECT sql_ FROM sqlmap WHERE key_ = ?", String.class, key);
        }
        catch(EmptyResultDataAccessException e)
        {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateSql(String key, String sql) {
        if(jdbcTemplate.update("UPDATE sqlmap SET sql_ = ? WHERE key_ = ?", sql, key) == 0)
            throw new RuntimeException();
    }

    @Override
    public void updateSql(Map<String, String> sqlmap) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(@NotNull TransactionStatus status) {
                for(Map.Entry<String, String> entry : sqlmap.entrySet()) {
                    updateSql(entry.getKey(), entry.getValue());
                }
            }
        });
    }
}
