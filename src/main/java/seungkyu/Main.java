package seungkyu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        ApplicationContext context =
                new GenericXmlApplicationContext("applicationContext.xml");

        UserDao userDao = context.getBean("userDao", UserDao.class);

        User createdUser = User.builder()
                .id("seungkyu")
                .name("승규")
                .password("password")
                .build();

        userDao.add(createdUser);

        System.out.println(createdUser.getId() + "계정을 등록했습니다.");

        User readUser = userDao.get("seungkyu");

        if(!readUser.getName().equals(createdUser.getName()))
        {
            System.out.println("테스트 실패 - name");
        }
        else if(!readUser.getPassword().equals(createdUser.getPassword()))
        {
            System.out.println("테스트 실패 - password");
        }
        else
        {
            System.out.println("조회 테스트 성공");
        }
    }
}