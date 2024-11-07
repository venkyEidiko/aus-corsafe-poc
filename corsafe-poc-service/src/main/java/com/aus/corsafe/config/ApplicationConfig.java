package com.aus.corsafe.config;

import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

    public static  String BASE_URL="https://dsm-1.tasklist.camunda.io/6feae57c-2f49-4861-a383-7c43f82db777/v1";

    public static String BASE_URL_CAMUNDA_TOKEN = "https://login.cloud.camunda.io";

    public static final String PASSWORD_MISMATCH_MESSAGE = "Passwords do not match";

    public static final String USER_NOT_FOUND_MESSAGE = "User not found with this email ID";

    public static final String EMAIL_NOT_REGISTERED_MESSAGE = "Email not registered with : ";

    public static final String PASSWORD_UPDATE_SUCCESS = "Password updated successfully";


}
