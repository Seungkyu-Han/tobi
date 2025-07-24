package seungkyu;

public interface MailSender {

    void sendUpgradeEmail(User user);
    void sendUpgradeEmail(User[] users);
}
