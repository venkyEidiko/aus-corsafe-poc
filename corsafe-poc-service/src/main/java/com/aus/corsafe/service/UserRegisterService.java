package com.aus.corsafe.service;

import com.aus.corsafe.dto.UserRegisterDto;
import com.aus.corsafe.entity.SecurityQuestion;


import java.util.List;

public interface UserRegisterService {

    public UserRegisterDto register(UserRegisterDto userRegisterDto);
    public List<SecurityQuestion> getAllSecurityQuestion();
}
