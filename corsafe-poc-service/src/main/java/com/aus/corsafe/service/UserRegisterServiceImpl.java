package com.aus.corsafe.service;

import com.aus.corsafe.dto.MapperClass;
import com.aus.corsafe.dto.UserRegisterDto;
import com.aus.corsafe.entity.SecurityQuestion;
import com.aus.corsafe.entity.SecurityQuestionKey;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.repository.SecurityQuestionRepository;
import com.aus.corsafe.repository.UserRegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRegisterServiceImpl implements UserRegisterService{
    @Autowired
    public MapperClass mapperClass;
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
    public UserRegisterDto register(UserRegisterDto userRegisterDto) {

      //  userRegisterDto.setPassword(encoder.encode( userRegister.getPassword()));

         UserRegister userRegister=mapperClass.userRegisterDtoTOUserRegister(userRegisterDto);

          userRegister.setPassword(encoder.encode(userRegister.getPassword()));

        return mapperClass.userRegisterTODto( userRegisterRepo.save(userRegister));
    }



    @Override
    public List<SecurityQuestion> getAllSecurityQuestion() {
       return securityQuestionRepository.findAll();
    }

//    @Override
//    public String addSecurityQuestionAnswerByUserId(List<SecurityQuestionKey> securityQuestionKey, Integer userId) {
//      UserRegister userRegister = userRegisterRepo.findById(userId).orElseThrow(()->new RuntimeException("User does not exist !"));
//      List<SecurityQuestionKey> list=userRegister.getSecurityQuestionList();
//
//      if(list==null){
//          List<SecurityQuestionKey> newList= new ArrayList<>();
//          newList.addAll(securityQuestionKey);
//      }else{
//          list.addAll(securityQuestionKey);
//      }
//        return "Added successfully !";
//    }

}
