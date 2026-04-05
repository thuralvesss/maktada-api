package br.com.maktaba.config;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmailRecuperacao(String destinatario, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject("Recuperação de senha - Maktaba");
        message.setText("Olá!\n\nClique no link abaixo para redefinir sua senha:\n\n"
                + "http://localhost:8080/recuperar-senha/redefinir?token=" + token
                + "\n\nEste link expira em 30 minutos.\n\nMaktaba");

        mailSender.send(message);
    }
}