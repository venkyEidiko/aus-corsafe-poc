package com.aus.corsafe.service;

import com.aus.corsafe.entity.SecurityQuestion;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.repository.SecurityQuestionRepository;
import com.aus.corsafe.repository.UserRegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegisterServiceImpl implements UserRegisterService{

    public PasswordEncoder encoder;
    public UserRegisterRepo userRegisterRepo;
    private SecurityQuestionRepository securityQuestionRepository;

    public UserRegisterServiceImpl(UserRegisterRepo userRegisterRepo,PasswordEncoder encoder, SecurityQuestionRepository securityQuestionRepository)
    {
        this.userRegisterRepo = userRegisterRepo;
        this.securityQuestionRepository= securityQuestionRepository;
        this.encoder=encoder;
    }

    @Override
    public UserRegister register(UserRegister userRegister) {
        userRegister.setPassword(encoder.encode( userRegister.getPassword()));
      return  userRegisterRepo.save(userRegister);
    }

    @Override
    public List<SecurityQuestion> getAllSecurityQuestion() {
       return securityQuestionRepository.findAll();
    }

}
