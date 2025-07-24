package seungkyu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "sqlmap")
public class SqlMap {

    private List<SqlType> queries;
}
