package seungkyu;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

public class JaxbTest {

    @Test
    public void readSqlMap() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SqlMap.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        SqlMap sqlmap = (SqlMap) unmarshaller.unmarshal(
                getClass().getResourceAsStream("/sqlmap.xml")
        );

        System.out.println(sqlmap);
    }

}
