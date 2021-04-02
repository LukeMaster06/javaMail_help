package mainPackage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailUtil {
    public static void sendMail(String recipient) throws Exception {
        System.out.println("preparing things. dont panic yet");
        Properties properties = new Properties();

        properties.put("mail.stmp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "test_email_not_real@gmail.com";
        String password = "notGonnaHappen";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient);

        Transport.send(message);
        System.out.println("message was hopefully sent");
    }

    /*
    The method below has been giving me the most trouble. It is different from the one in the video because when IntelliJ
    added a try/catch line, it added it in a different way than the video. I tried tampering with it to see what would work, but
    nothing helped. So, I'm stuck with this hot garbage.
     */
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("my first email from java");
            message.setText("cool thing. hi there. hope you get this. i wanted to talk to you about your cars extended warranty");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
