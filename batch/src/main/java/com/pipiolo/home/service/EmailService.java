package com.pipiolo.home.service;

import com.pipiolo.home.dto.HomeRequest;
import com.pipiolo.home.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final UserService userService;

    public void send(HomeRequest request) {
        List<UserResponse> users = userService.getUsersContainsRegion(request.region());

        for (UserResponse user : users) {
            send(user.email(), request.region(), "House Name : " + request.houseName() +
                    "Region : " + request.region() +
                    "Day : " + request.subscriptionStartDay());
        }
    }

    private void send(String email, String title, String message) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(message);
        };

        mailSender.send(preparator);
    }

}
