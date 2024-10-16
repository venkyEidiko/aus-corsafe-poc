package com.aus.corsafe.dto;


import com.aus.corsafe.entity.UserRegister;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperClass {

    @Autowired
    public ModelMapper modelMapper;

    public UserRegisterDto userRegisterTODto(UserRegister userRegister)
    {
        return   modelMapper.map(userRegister,UserRegisterDto.class);
    }
    public UserRegister userRegisterDtoTOUserRegister(UserRegisterDto userRegisterDto)
    {
        return   modelMapper.map(userRegisterDto,UserRegister.class);
    }

}
