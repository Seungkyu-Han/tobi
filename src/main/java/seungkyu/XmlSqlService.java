package seungkyu;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class XmlSqlService implements SqlService{

    private final SqlReader sqlReader;

    private final SqlRegistry sqlRegistry;

    @PostConstruct
    public void loadSql(){
        this.sqlReader.read(this.sqlRegistry);
    }

    public String getSql(String key){
        return this.sqlRegistry.findSql(key);
    }
}