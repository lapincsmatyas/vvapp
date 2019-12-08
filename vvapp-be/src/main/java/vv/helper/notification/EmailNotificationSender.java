package vv.helper.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import vv.model.Review;

import java.util.List;

@Service
public class EmailNotificationSender {

    @Autowired
    public JavaMailSender emailSender;

    public void sendEmailOfReview(String[] to, Review review){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Új értékelés: " + review.getParticipation().getEvent().getName() + ":" + review.getSenior().getName());

        String text = "Új értékelés történt: ";
        text += "<ul>";
        text += "<li> Esemény neve: " + review.getParticipation().getEvent().getName() + "</li>";
        text += "<li> Értékelő neve: " + review.getSenior().getName() + "</li>";
        text += "<li> Értékelés szövege: " + review.getText() + "</li>";
        text += "</ul>";

        message.setText(text);

        emailSender.send(message);
    }
}
