package com.jwtJpa.jwtJpa.Service;

import com.jwtJpa.jwtJpa.DTO.UserDto;
import com.jwtJpa.jwtJpa.DTO.UserJwtDto;
import com.jwtJpa.jwtJpa.Entity.UserEntity;
import com.jwtJpa.jwtJpa.Mapper.UserMapper;
import com.jwtJpa.jwtJpa.Repositoty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRegister {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity registerUser(UserEntity user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("User Already Exist");
        }
        user.setCreatedAt(LocalDate.now());
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setMobileNo(user.getMobileNo());
        System.out.println("user" + user + "user.getPassword()" + user.getPassword());
        return userRepository.save(user);
    }

}
