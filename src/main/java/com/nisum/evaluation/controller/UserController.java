package com.nisum.evaluation.controller;

import com.nisum.evaluation.dto.CreateUserRequest;
import com.nisum.evaluation.dto.UserItem;
import com.nisum.evaluation.mapper.UserMapper;
import com.nisum.evaluation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;


    @Operation(summary = "Creates a new user in the system.",
            description = "A user info with a token for accessing protected resources is returned."
    )
    @ResponseStatus(CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserItem> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        var user = userMapper.toUser(createUserRequest);
        var userCreated = userService.saveUser(user);
        return ResponseEntity.status(CREATED).body(userMapper.toUserItem(userCreated));
    }

    @Operation(summary = "Gets all registered users in the system.",
            description = "A list of users is returned"
    )
    @ResponseStatus(OK)
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<UserItem>> getAllUsers() {
        List<UserItem> allUsers = userService.findAllUsers()
                .stream().map(userMapper::toUserItem)
                .collect(Collectors.toList());
        return ResponseEntity.status(OK).body(allUsers);

    }

}
