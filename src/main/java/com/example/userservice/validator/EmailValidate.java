package com.example.userservice.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContactEmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidate {

    String message() default "Invalid Email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
