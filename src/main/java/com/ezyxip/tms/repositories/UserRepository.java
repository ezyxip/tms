package com.ezyxip.tms.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ezyxip.tms.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
    Optional<UserEntity> findByEmail(String email);
}
