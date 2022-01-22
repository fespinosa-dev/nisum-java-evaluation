package com.nisum.evaluation.service;

import com.nisum.evaluation.TestDataFactory;
import com.nisum.evaluation.model.Phone;
import com.nisum.evaluation.model.User;
import com.nisum.evaluation.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    public static final int FIRST_PHONE = 0;

    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtTokenGenerator jwtTokenGenerator;

    @InjectMocks
    private UserService userService;


    @Test
    void saveUser() {
        var user = TestDataFactory.newUserStub();
        Mockito.when(userRepository.save(user)).thenReturn(user);

        var savedUser = userService.saveUser(user);

        assertSavedUser(savedUser);

    }


    private void assertSavedUser(User savedUser) {
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUserId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("Juan Rodriguez");
        assertThat(savedUser.getEmail()).isEqualTo("juan@rodriguez.do");
        assertThat(savedUser.getPassword()).isEqualTo("0123456789$abcdefgAB");
        assertThat(savedUser.getCreated()).isNotNull();
        assertThat(savedUser.getLastLogin()).isNotNull();
        assertThat(savedUser.getModified()).isNotNull();
        var firstPhone = savedUser.getPhones().get(FIRST_PHONE);
        assertUsersPhone(firstPhone, savedUser.getUserId());

    }

    private void assertUsersPhone(Phone phone, UUID userId) {
        assertThat(phone).isNotNull();
        assertThat(phone.getCityCode()).isEqualTo("1");
        assertThat(phone.getPhoneId()).isNotNull();
        assertThat(phone.getUserId()).isEqualTo(userId);
        assertThat(phone.getNumber()).isEqualTo("123456");
        assertThat(phone.getCountryCode()).isEqualTo("52");
    }


}