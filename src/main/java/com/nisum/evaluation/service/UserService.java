package com.nisum.evaluation.service;

import com.nisum.evaluation.exception.EmailAlreadyExistsException;
import com.nisum.evaluation.model.User;
import com.nisum.evaluation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtTokenGenerator jwtTokenGenerator;

    /**
     * Stores the user along with the generated token in the database
     *
     * @param user to be saved
     * @return savedUser
     */
    public User saveUser(User user) {
        User savedUser;
        try {
            var jwtToken = jwtTokenGenerator.generateJwtToken(user.getName());
            user.setToken(jwtToken);
            savedUser = userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException(ex);
        }
        return savedUser;
    }

}
