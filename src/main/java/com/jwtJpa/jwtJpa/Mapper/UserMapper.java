package com.jwtJpa.jwtJpa.Mapper;
import org.mapstruct.Mapper;


import com.jwtJpa.jwtJpa.DTO.UserDto;
import com.jwtJpa.jwtJpa.Entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);

}
