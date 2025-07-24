package seungkyu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.InputStream;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
public class OxmTest {

    @Test
    public void unmarshallSqlMap() throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try (InputStream inputStream = getClass().getResourceAsStream("/sqlmap.yml")) {
            assert inputStream != null;

            SqlMap sqlMap = mapper.readValue(inputStream, SqlMap.class);
            System.out.println(sqlMap);
        }
    }
}
