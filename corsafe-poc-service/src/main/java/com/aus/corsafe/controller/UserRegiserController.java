package com.aus.corsafe.controller;
import com.aus.corsafe.dto.Login;
import com.aus.corsafe.dto.UserRegisterDto;
import com.aus.corsafe.dto.LoginResponseCls;
import com.aus.corsafe.entity.ResponseModel;
import com.aus.corsafe.entity.SecurityQuestion;
import com.aus.corsafe.entity.SecurityQuestionKey;
import com.aus.corsafe.exceptions.BadCrediantialsCls;
import com.aus.corsafe.repository.SecurityQuestionKeyRepository;
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
public class UserRegiserController {

    // this is for jwt and refresh token
    @Autowired
    private CommonResponse<LoginResponseCls> commonResponse;

    private UserRegisterService userRegisterService;
@Autowired
private SecurityQuestionKeyRepository securityQuestionKeyRepository;
    @Autowired
    private LoginService loginService;

    public UserRegiserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseModel<Object>> register(@RequestBody UserRegisterDto userRegister) {

        log.info("user dto {}", userRegister);
        UserRegisterDto register = userRegisterService.register(userRegister);
        return new CommonResponse<>().prepareSuccessResponseObject(register, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel<LoginResponseCls>> login(@RequestBody Login login) {

        log.info("login is executing && email is:" + login.getEmail() + " password is: " + login.getPassword());

        try {

            LoginResponseCls res = loginService.tokenGenarationMethod(login);
            return commonResponse.prepareSuccessResponseObject(res, HttpStatus.OK);
        } catch (Exception e) {
            log.info("error:  " + e.toString());
            return commonResponse.prepareFailedResponse("Invalid !!");
        }

    }

    @GetMapping("/getAllSecurityQuestion")
    public ResponseEntity<ResponseModel<Object>> getAllSecurityQuestion() {
        List<SecurityQuestion> questionList = userRegisterService.getAllSecurityQuestion();
        return new CommonResponse<>().prepareSuccessResponseObject(questionList, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testMethod() {
        log.info("Test method is executing");
        return new ResponseEntity<>("test successfull", HttpStatus.OK);
    }

    @PostMapping("/refreshToken/{token}")
    public ResponseEntity<ResponseModel<Object>> refreshTokenMethod(@PathVariable String token) {

        try {
            String accessToken = loginService.refreshTokenGenaration(token);
            return  new CommonResponse<>().prepareSuccessResponseObject(accessToken, HttpStatus.OK);

        } catch (BadCrediantialsCls e) {
            return new CommonResponse<>().prepareFailedResponse(e.getMessage());
        } catch (Exception e) {
            return new CommonResponse<>().prepareFailedResponse("bad credentials or token");
        }

    }
  /*  @GetMapping("getquestionByUserId/{userId}")
    public ResponseEntity<ResponseModel<Object>> getquestionByUserId(@PathVariable("userId") Integer userId){
       log.info("useid { }",userId);

        List<SecurityQuestionKey> allByUserId = securityQuestionKeyRepository.findAllByUserId(userId);
      log.info("allByUserId { }",allByUserId);

        return new CommonResponse<>().prepareSuccessResponseObject(allByUserId,HttpStatus.OK);
    }*/

}
