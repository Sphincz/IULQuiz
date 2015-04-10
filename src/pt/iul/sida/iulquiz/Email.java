package pt.iul.sida.iulquiz;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	public void Confirmacao_Envia_Password(Utilizador user, String generatedPassword) {
		final String username = "quiziul@gmail.com";
        final String password = "iulquiz2015";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("quiziul@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(user.getEmail()));
            message.setSubject("Bem-vindo ao IULQuiz, "+user.getEmail()+"!");
            message.setText("Bem vindo ao IULQuiz"
                + "\n\nAcabámos de confirmar o teu registo no IULQuiz e a password para fazeres login é:"
                + "\n\nPassword: "+generatedPassword+""
                + "\n\nObrigado!");

            Transport.send(message);

            System.out.println("Email enviado com sucesso!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}

}
