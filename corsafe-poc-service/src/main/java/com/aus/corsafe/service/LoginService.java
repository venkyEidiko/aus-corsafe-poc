package com.aus.corsafe.service;

import com.aus.corsafe.config.JwtService;
import com.aus.corsafe.config.MyUserDetailasService;
import com.aus.corsafe.dto.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailasService myUserDetailasService;

    @Autowired
    private JwtService jwtService;

    public String tokenGenarationMethod(Login login) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

            if (authenticate.isAuthenticated()) {
                return jwtService.genarateJwtToken(myUserDetailasService.loadUserByUsername(login.getEmail()));

            } else {
                throw new RuntimeException("not authenticated or invalid credintials!!");
            }
        } catch (Exception e) {
            System.out.println("error is loginservice catch block::: " + e.toString());
            throw new RuntimeException("error is catch block login service: " + e.toString());

        }
    }
}
