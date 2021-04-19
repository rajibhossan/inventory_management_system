package service;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
    
    private ConfigurationEmail config;

    public Email(ConfigurationEmail config) {
        this.config = config;
    }

    
    public void setConfigurationEmail(ConfigurationEmail config) {
        this.config = config;
    }

    private Properties getProperty(String TYPE) {
        Properties props = new Properties();
        
        if(TYPE.equals("smtp")){        
            props.put("mail.smtp.user", "username");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.EnableSSL.enable", "true");

            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
        }
       if(TYPE.equals("pop3")){        
            props.put("mail.smtp.user", "username");
            props.put("mail.pop3.host", "smtp.gmail.com");
            props.put("mail.pop3.port", "465");
            props.put("mail.debug", "true");
            props.put("mail.pop3.auth", "true");
            props.put("mail.pop3.starttls.enable", "true");
            props.put("mail.pop3.EnableSSL.enable", "true");

            props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.pop3.socketFactory.fallback", "false");
            props.setProperty("mail.pop3.port", "465");
            props.setProperty("mail.pop3.socketFactory.port", "465");
        }
    
        return props;
    }
    
    private Session session = Session.getInstance(getProperty("pop3"), new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(config.getUserName(), config.getPassword());
            
        }
    });

    private InternetAddress[] sendAddress() throws AddressException {
        return config.sendTo();
    }

    public void sendAttatchFileEmail() {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(config.getUserName()));
            message.setRecipients(Message.RecipientType.TO, sendAddress());
            message.setSubject("Invoice For House Rent");

            MimeMultipart multipart = new MimeMultipart("related");

            //Sending HTML Text
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<font color='red'><h1>Hi Your Booking Invoice</color></h1></font>", "text/html");
            multipart.addBodyPart(messageBodyPart);

            //Sending File
            String filename = config.getFile();
            DataSource source = new FileDataSource(filename);

            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            System.out.println(mex.getMessage());
        }
    }
}
