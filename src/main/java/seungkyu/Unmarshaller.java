package seungkyu;

import javax.xml.transform.Source;
import java.io.IOException;

public interface Unmarshaller {

    boolean supports(Class<?> clazz);

    Object unmarshal(Source source) throws IOException;
}
