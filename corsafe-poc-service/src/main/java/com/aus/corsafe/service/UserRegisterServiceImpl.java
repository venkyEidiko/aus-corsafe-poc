package com.aus.corsafe.service;

import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.repository.UserRegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService{
    @Autowired
    public PasswordEncoder encoder;
    public UserRegisterRepo userRegisterRepo;

    public UserRegisterServiceImpl(UserRegisterRepo userRegisterRepo)
    {
        this.userRegisterRepo = userRegisterRepo;

    }

    @Override
    public UserRegister register(UserRegister userRegister) {
        userRegister.setPassword(encoder.encode( userRegister.getPassword()));
      return  userRegisterRepo.save(userRegister);


    }
}
