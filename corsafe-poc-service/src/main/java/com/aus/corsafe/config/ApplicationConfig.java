package com.aus.corsafe.config;

import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

    public static  String BASE_URL="https://jfk-1.tasklist.camunda.io/c4765f54-b30c-4eba-b09f-2914741db450/v1";

    public static String BASE_URL_CAMUNDA_TOKEN = "https://login.cloud.camunda.io";

    public static final String PASSWORD_MISMATCH_MESSAGE = "Passwords do not match";

    public static final String USER_NOT_FOUND_MESSAGE = "User not found with this email ID";

    public static final String EMAIL_NOT_REGISTERED_MESSAGE = "Email not registered with : ";

    public static final String PASSWORD_UPDATE_SUCCESS = "Password updated successfully";


}
