package seungkyu;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailSenderImpl implements MailSender{

    public void sendUpgradeEmail(User user){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "mail.seungkyu.com");
        Session session = Session.getInstance(properties);

        MimeMessage mimeMessage = new MimeMessage(session);

        try
        {
            mimeMessage.setFrom(new InternetAddress("seungkyu@seungkyu.com"));
            mimeMessage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            mimeMessage.setSubject("승급 안내");
            mimeMessage.setText(user.getLevel() + "로 승급하셨습니다.");

            Transport.send(mimeMessage);
        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void sendUpgradeEmail(User[] users) {
        for(User user : users) this.sendUpgradeEmail(user);
    }
}