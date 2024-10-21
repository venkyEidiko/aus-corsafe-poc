package com.aus.corsafe.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    public static final String PASSWORD_MISMATCH_MESSAGE = "Passwords do not match";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found with this email ID";
    public static final String EMAIL_NOT_REGISTERED_MESSAGE = "Email not registered with : ";
    public static final String PASSWORD_UPDATE_SUCCESS = "Password updated successfully";


}
