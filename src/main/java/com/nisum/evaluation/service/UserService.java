package com.nisum.evaluation.service;

import com.nisum.evaluation.exception.EmailAlreadyExistsException;
import com.nisum.evaluation.model.User;
import com.nisum.evaluation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;

    public User saveUser(User user) {
        User savedUser;
        try {
            var generatedToken = jwtTokenService.generateToken(user.getName());
            user.setToken(generatedToken);
            savedUser = userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException(ex);
        }
        return savedUser;
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User findUserByName(String username) {
        return userRepository.findUserByName(username);
    }

    public List<User> findAllUsers() {
        List<User> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }

}
