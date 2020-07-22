package com.example.userservice.event.listener;

import com.example.userservice.event.SuccessfulRegistrationEvent;
import com.example.userservice.model.User;
import com.example.userservice.model.VerificationToken;
import com.example.userservice.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SuccessfulRegistrationEventListener implements ApplicationListener<SuccessfulRegistrationEvent> {

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Override
    public void onApplicationEvent(SuccessfulRegistrationEvent successfulRegistrationEvent) {

        User registerUser = successfulRegistrationEvent.getRegisteredUser();

        VerificationToken verificationToken = new VerificationToken(registerUser);

        verificationTokenRepository.save(verificationToken);


        //TODO: Send Email to the User.

        //TODO: Allocate Resource to the User.

    }
}
