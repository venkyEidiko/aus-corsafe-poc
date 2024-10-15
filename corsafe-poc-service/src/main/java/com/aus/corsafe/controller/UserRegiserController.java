package com.aus.corsafe.controller;

import com.aus.corsafe.dto.Login;

import com.aus.corsafe.dto.LoginResponseCls;

import com.aus.corsafe.entity.ResponseModel;
import com.aus.corsafe.entity.SecurityQuestion;

import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.exceptions.BadCrediantialsCls;
import com.aus.corsafe.response.CommonResponse;
import com.aus.corsafe.service.LoginService;
import com.aus.corsafe.service.UserRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@Slf4j
public class UserRegiserController {

    @Autowired
    private CommonResponse<LoginResponseCls> commonResponse;

    @Autowired
    private   CommonResponse<String> commonResponse1;

    private  UserRegisterService userRegisterService;

    @Autowired
    private LoginService loginService;

    public UserRegiserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserRegister> register(@RequestBody UserRegister userRegister) {
        log.info("regiser method is executing");
        return new ResponseEntity<>(userRegisterService.register(userRegister), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel<LoginResponseCls>> login(@RequestBody Login login) {

        log.info("login is executing && email is:" + login.getEmail() + " password is: " + login.getPassword());

       // return new ResponseEntity<>(loginService.tokenGenarationMethod(login), HttpStatus.OK);
        try{
            LoginResponseCls res = loginService.tokenGenarationMethod(login);
            return commonResponse.prepareSuccessResponseObject(res,HttpStatus.OK);
        }
        catch(Exception e) {
            log.info("error:  "+e.toString());
            return commonResponse.prepareFailedResponse("Invalid !!");
        }

    }
    @GetMapping("/getAllSecurityQuestion")
    public  ResponseEntity<ResponseModel<Object>> getAllSecurityQuestion(){
       List<SecurityQuestion> questionList=userRegisterService.getAllSecurityQuestion();
        return new CommonResponse<>().prepareSuccessResponseObject(questionList,HttpStatus.OK);
    }



    @GetMapping("/test")
    public ResponseEntity<String> testMethod() {
        log.info("Test method is executing");
        return new ResponseEntity<>("test successfull", HttpStatus.OK);
    }

    @PostMapping("/refreshToken/{token}")
    public ResponseEntity<ResponseModel<String>> refreshTokenMethod(@PathVariable String token) {
        //return new ResponseEntity<>(loginService.refreshTokenGenaration(token), HttpStatus.CREATED);
        try{
            String accessToken  = loginService.refreshTokenGenaration(token);
            return commonResponse1.prepareSuccessResponseObject(accessToken,HttpStatus.OK);

        }
        catch (BadCrediantialsCls e) {
            return commonResponse1.prepareFailedResponse(e.getMessage());
        }
        catch (Exception e){
            return commonResponse1.prepareFailedResponse("bad credintials or token");
        }

    }


}
