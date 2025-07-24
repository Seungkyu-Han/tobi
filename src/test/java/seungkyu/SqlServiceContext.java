package seungkyu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class SqlServiceContext {

    @Bean
    public SqlRegistry sqlRegistry() {
        return new ConcurrentHashMapSqlRegistry();
    }

    @Bean
    public SqlReader sqlReader() {
        return new YmlSqlReader("/sqlmap.yml");
    }

    @Bean
    public SqlService sqlService() {
        return new YmlSqlService(sqlReader(), sqlRegistry());
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("embeddedDatabase")
                .setType(EmbeddedDatabaseType.H2)
                .addScript("/schema.sql")
                .build();
    }
}