package com.example.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class VerificationToken {

    private static final int VALIDITY_TIME = 4*60; // Expiry token time

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class)
    private User user;

    private Date expiryTime;

    public VerificationToken() {
    }

    public VerificationToken(User user){
        String token = generateRandomUniqueToken();
        this.token = token;

        this.user = user;
        this.expiryTime = calculateExpiryTime();

    }

    public void updateToken(){

        this.token = generateRandomUniqueToken();
        this.expiryTime = calculateExpiryTime();

        //TODO: generate new token string;

        //TODO: calculate new expiry date;
    }

    private String generateRandomUniqueToken(){

        return UUID.randomUUID().toString();
    }

    private Date calculateExpiryTime(){

        Calendar calendar = Calendar.getInstance();

        Date currentTimeAndDate= new Date();

        calendar.setTimeInMillis(currentTimeAndDate.getTime());

        calendar.add(Calendar.MINUTE, VALIDITY_TIME);

        return calendar.getTime();
    }
}
