package com.aus.corsafe.controller;

import com.aus.corsafe.dto.Login;
import com.aus.corsafe.dto.UserRegisterDto;
import com.aus.corsafe.entity.ResponseModel;
import com.aus.corsafe.entity.SecurityQuestion;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.response.CommonResponse;
import com.aus.corsafe.service.LoginService;
import com.aus.corsafe.service.UserRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@Slf4j
public class UserRegiserController {

    private  UserRegisterService userRegisterService;

    @Autowired
    private LoginService loginService;

    public UserRegiserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseModel<Object>> register(@RequestBody UserRegisterDto userRegister) {

         log.info("user dto {}",userRegister);
        UserRegisterDto register = userRegisterService.register(userRegister);

       return  new CommonResponse<>().prepareSuccessResponseObject(register,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {

        return new ResponseEntity<>(loginService.tokenGenarationMethod(login),HttpStatus.OK);
    }
    @GetMapping("/getAllSecurityQuestion")
    public  ResponseEntity<ResponseModel<Object>> getAllSecurityQuestion(){
       List<SecurityQuestion> questionList=userRegisterService.getAllSecurityQuestion();
        return new CommonResponse<>().prepareSuccessResponseObject(questionList,HttpStatus.OK);
    }


}
