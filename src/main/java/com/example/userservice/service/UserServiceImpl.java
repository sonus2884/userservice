package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.event.SuccessfulRegistrationEvent;
import com.example.userservice.model.User;
import com.example.userservice.model.VerificationToken;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @Override
    public User registerUser(UserDto userDto) {

        if(userRepository.findByEmail(userDto.getEmail()) != null){
            //TODO: Some Exception
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setActive(false);
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); //TODO : Encrypt Password.
        User savedUser = userRepository.save(user);

        applicationEventPublisher.publishEvent(
                new SuccessfulRegistrationEvent(savedUser)
        );

        return savedUser;
    }

    @Override
    public User validateUser(String token) {

        // TODO: Check Token epo if there is that token
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if(verificationToken == null){
            return null;
        }

        if (verificationToken.getExpiryTime().getTime() >  new Date().getTime()){

            //Token is not yet Expired;

            User verifiedUser = verificationToken.getUser();
            verifiedUser.setActive(true);

            userRepository.save(verifiedUser);
            return  verifiedUser;

        }else{
            return null;
        }

        // TODO: if it exists -> check if it is for the same user.
        //TODO: check if it is not expired yed.

        // if not expired => return verified user

        //else : return null;
//        return null;
    }
}
