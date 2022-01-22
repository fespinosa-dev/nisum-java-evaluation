package com.nisum.evaluation.mapper;


import com.nisum.evaluation.dto.CreateUserRequest;
import com.nisum.evaluation.dto.CreateUserResponse;
import com.nisum.evaluation.dto.PhoneItem;
import com.nisum.evaluation.model.Phone;
import com.nisum.evaluation.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserRequest userToUserDto(User user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "token", ignore = true)
    User toUser(CreateUserRequest createUserRequest);

    CreateUserResponse toCreateUserResponse(User user);

    @Mapping(target = "phoneId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Phone toPhone(PhoneItem phoneItem);

    PhoneItem toPhoneItem(Phone phone);
}
