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
        message.setFrom("lapincs.matyas@gmail.com");
        message.setSubject("Új értékelés: " + review.getParticipation().getEvent().getName() + " - " + review.getSenior().getName());

        String text = "Új értékelés történt: \n";
        text += "\nEsemény neve: " + review.getParticipation().getEvent().getName();
        text += "\nÉrtékelő neve: " + review.getSenior().getName();
        text += "\nÉrtékelt senior: " + review.getParticipation().getSenior().getName();
        text += "\nÉrtékelés szövege: " + review.getText();
        text += "\n";

        message.setText(text);

        emailSender.send(message);
    }
}
