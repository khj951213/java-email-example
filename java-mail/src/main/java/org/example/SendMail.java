package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SendMail {

    private String username;
    private String password;
    private String host;
    private int port;
    private String title;
    private String content;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SendMail() { }

    public SendMail(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public boolean send(String to) {
        var username = this.username;
        var password = this.password;
        var props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", host);

        var session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true);

        try {
          var message = new MimeMessage(session);
          message.setFrom(new InternetAddress(username));
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
          message.setSubject(title);
          message.setText(content);
          Transport.send(message);
          System.out.println("Message sent successfully...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
