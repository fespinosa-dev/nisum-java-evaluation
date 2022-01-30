package com.nisum.evaluation.repository;

import com.nisum.evaluation.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    User findUserByName(String username);
}
