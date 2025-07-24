package seungkyu;


public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest{

    @Override
    protected UpdatableSqlRegistry injectSqlRegistry() {
        return new ConcurrentHashMapSqlRegistry();
    }
}
