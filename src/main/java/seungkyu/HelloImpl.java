package seungkyu;

public class HelloImpl implements Hello {

    public String sayHello(String name) {
        return "hello " + name;
    }

    public String sayHi(String name) {
        return "hi " + name;
    }

    public String sayBye(String name) {
        return "bye " + name;
    }

    @Override
    public int return1() {
        return 1;
    }
}
