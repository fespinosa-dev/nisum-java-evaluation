package com.nisum.evaluation;

import com.nisum.evaluation.dto.CreateUserRequest;
import com.nisum.evaluation.dto.CreateUserResponse;
import com.nisum.evaluation.dto.PhoneItem;
import com.nisum.evaluation.model.Phone;
import com.nisum.evaluation.model.User;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class TestDataFactory {

    private TestDataFactory() {

    }

    public static User newUserStub() {
        var userId = UUID.fromString("dac0f858-4042-40b6-96a4-cee17ed23a29");
        return User.builder()
                .userId(userId)
                .name("Juan Rodriguez")
                .email("juan@rodriguez.do")
                .password("0123456789$abcdefgAB")
                .phones(List.of(newPhoneStub(userId)))
                .active(true)
                .created(Instant.now())
                .lastLogin(Instant.now())
                .modified(Instant.now())
                .build();
    }

    private static Phone newPhoneStub(UUID userId) {
        return Phone.builder()
                .userId(userId)
                .phoneId(UUID.randomUUID())
                .cityCode("1")
                .countryCode("52")
                .number("123456")
                .build();
    }

    public static CreateUserRequest newCreateUserRequestStub(){
        return CreateUserRequest.builder()
                .name("Juan Rodriguez")
                .password("0123456789$abcdefgAB")
                .email("juan@rodriguez.do")
                .phones(List.of(newPhoneItemStub()))
                .build();
    }


    private static PhoneItem newPhoneItemStub(){
        return PhoneItem.builder()
                .number("123456")
                .cityCode("1")
                .countryCode("52")
                .build();

    }


}
