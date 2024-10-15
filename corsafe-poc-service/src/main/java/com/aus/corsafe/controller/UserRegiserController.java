package com.aus.corsafe.controller;

import com.aus.corsafe.dto.Login;
import com.aus.corsafe.dto.LoginResponseCls;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.service.LoginService;
import com.aus.corsafe.service.UserRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
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
        log.info("regiser method is executing");
        return new ResponseEntity<>(userRegisterService.register(userRegister), HttpStatus.CREATED);

    }

    /*
        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody Login login) {

            log.info("login is executing && email is:" + login.getEmail() + " password is: " + login.getPassword());

            return new ResponseEntity<>(loginService.tokenGenarationMethod(login), HttpStatus.OK);

        }
    */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseCls> login(@RequestBody Login login) {

        log.info("login is executing && email is:" + login.getEmail() + " password is: " + login.getPassword());

        return new ResponseEntity<>(loginService.tokenGenarationMethod(login), HttpStatus.OK);

    }


    @GetMapping("/test")
    public ResponseEntity<String> testMethod() {
        log.info("Test method is executing");
        return new ResponseEntity<>("test successfull", HttpStatus.OK);
    }

    @PostMapping("/refreshToken/{token}")
    public ResponseEntity<String> refreshTokenMethod(@PathVariable String token) {
        return new ResponseEntity<>(loginService.refreshTokenGenaration(token), HttpStatus.CREATED);

    }


}
