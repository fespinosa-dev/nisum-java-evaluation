package com.nisum.evaluation.controller;

import com.nisum.evaluation.dto.CreateUserRequest;
import com.nisum.evaluation.dto.CreateUserResponse;
import com.nisum.evaluation.mapper.UserMapper;
import com.nisum.evaluation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        var user = userMapper.toUser(createUserRequest);
        var userCreated = userService.saveUser(user);
        return userMapper.toCreateUserResponse(userCreated);
    }

}
