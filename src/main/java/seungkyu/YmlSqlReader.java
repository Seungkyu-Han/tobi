package seungkyu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class YmlSqlReader implements SqlReader {

    private final String path;

    @Override
    public void read(SqlRegistry sqlRegistry) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
            assert inputStream != null;

            SqlMap sqlMap = mapper.readValue(inputStream, SqlMap.class);

            for(SqlType sqlType: sqlMap.getQueries())
            {
                sqlRegistry.registerSql(sqlType.getKey(), sqlType.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
