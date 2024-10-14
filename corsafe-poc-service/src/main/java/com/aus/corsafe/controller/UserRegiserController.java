package com.aus.corsafe.controller;

import com.aus.corsafe.dto.Login;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.service.LoginService;
import com.aus.corsafe.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegiserController {

    private final UserRegisterService userRegisterService;

    @Autowired
    private LoginService loginService;

    @Autowired
    public UserRegiserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserRegister> register(@RequestBody UserRegister userRegister) {

        return new ResponseEntity<>(userRegisterService.register(userRegister), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {

//        return new ResponseEntity<>(userRegisterService.register(userRegister), HttpStatus.CREATED);

        return new ResponseEntity<>(loginService.tokenGenarationMethod(login),HttpStatus.OK);

    }

}
