package com.aus.corsafe.service;

import com.aus.corsafe.config.JwtService;
import com.aus.corsafe.config.MyUserDetailasService;
import com.aus.corsafe.dto.Login;
import com.aus.corsafe.dto.LoginResponseCls;
import com.aus.corsafe.exceptions.BadCrediantialsCls;
import com.aus.corsafe.exceptions.UnAuthorizedExceptionCls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailasService myUserDetailasService;

    @Autowired
    private JwtService jwtService;
/*
    public String tokenGenarationMethod(Login login) {
        log.info("tokenGenaration method in loginservice cls entered!!");
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

            if (authenticate.isAuthenticated()) {
                log.info("checking is authenticated or not!!");
                return jwtService.genarateJwtToken(myUserDetailasService.loadUserByUsername(login.getEmail()));

            } else {
                log.info("fails while checking authntication");
                //throw new RuntimeException("not authenticated or invalid credintials!!");
                throw new UnAuthorizedExceptionCls("Authenuthentication Failed !!");
            }
        } catch (Exception e) {
            System.out.println("error is loginservice catch block::: " + e.toString());
            //throw new RuntimeException("error is catch block login service: " + e.toString());
            throw new UnAuthorizedExceptionCls("Authenuthentication Failed !!");

        }
    }
*/
    //refresh token
    public String refreshTokenGenaration(String jwtToken) {

        String subject = jwtService.getSubjectFromToken(jwtToken);

        if (jwtService.isTokenValid(jwtToken)) {
            return jwtService.genarateRefreshToken(myUserDetailasService.loadUserByUsername(subject));

        } else {
            throw new BadCrediantialsCls("BadRequest!! ");

        }

    }

    //return jwt+refresh token after login success
    public LoginResponseCls tokenGenarationMethod(Login login) {
        log.info("tokenGenaration method in loginservice cls entered!!");
        String jwtToken="";
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

            if (authenticate.isAuthenticated()) {
                log.info("checking is authenticated or not!!");
                 jwtToken = jwtService.genarateJwtToken(myUserDetailasService.loadUserByUsername(login.getEmail()));

            } else {
                log.info("fails while checking authntication");
                //throw new RuntimeException("not authenticated or invalid credintials!!");
                throw new UnAuthorizedExceptionCls("Authenuthentication Failed !!");
            }
        } catch (Exception e) {
            System.out.println("error is loginservice catch block::: " + e.toString());
            //throw new RuntimeException("error is catch block login service: " + e.toString());
            throw new UnAuthorizedExceptionCls("Authenuthentication Failed !!");

        }

        return LoginResponseCls
                .builder()
                .jwtToken(jwtToken)
                .refreshToken(refreshTokenGenaration(jwtToken))
                .build();
    }


}
