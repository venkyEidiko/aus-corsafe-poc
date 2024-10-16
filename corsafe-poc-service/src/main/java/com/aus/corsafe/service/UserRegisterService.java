package com.aus.corsafe.service;

import com.aus.corsafe.entity.SecurityQuestion;
import com.aus.corsafe.entity.SecurityQuestionKey;
import com.aus.corsafe.entity.UserRegister;

import java.util.List;

public interface UserRegisterService {

    public UserRegister register(UserRegister userRegister);
    public List<SecurityQuestion> getAllSecurityQuestion();

  //  public String addSecurityQuestionAnswerByUserId(List<SecurityQuestionKey> securityQuestionKey, Integer userId);
}
