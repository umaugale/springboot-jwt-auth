package com.jwtJpa.jwtJpa.Repositoty;

import com.jwtJpa.jwtJpa.DTO.UserDto;
import com.jwtJpa.jwtJpa.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

}
