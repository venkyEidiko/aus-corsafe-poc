package com.aus.corsafe.config;

import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.repository.UserRegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.aus.corsafe.repository.UserRegisterRepo;

import java.util.Optional;

@Service
public class MyUserDetailasService implements UserDetailsService {

    @Autowired
    private UserRegisterRepo UserRegisterRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserRegister> userRegister = UserRegisterRepo.findByEmail(username);
        UserRegister res= userRegister.get();
        if(userRegister.isPresent()){
            return User.builder()
                    .username(res.getEmail())
                    .password(res.getPassword())
                    .build();

        }
        else{
            throw new UsernameNotFoundException("user not found!!");
        }

      //  return null;
    }
}
