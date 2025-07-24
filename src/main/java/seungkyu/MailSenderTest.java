package seungkyu;

import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MailSenderTest implements MailSender {

    private final List<String> history = new ArrayList<>();

    public void sendUpgradeEmail(@NonNull User user) {
        history.add(user.getEmail());
    }

    public void sendUpgradeEmail(@NonNull User[] users) {
        System.out.println("sendUpgradeEmail 호출");
        for (User user : users) System.out.println(user);
    }
}