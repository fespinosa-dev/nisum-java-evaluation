package com.nisum.evaluation.controller;

import com.nisum.evaluation.TestDataFactory;
import com.nisum.evaluation.dto.PhoneItem;
import com.nisum.evaluation.dto.UserItem;
import com.nisum.evaluation.model.User;
import com.nisum.evaluation.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest extends AbstractControllerTest{

    private static final int FIRST_PHONE = 0;

    @MockBean
    private UserService userService;


    @Test
    void saveUser() throws Exception {

        var createUserRequest = TestDataFactory.newCreateUserRequestStub();
        var savedUser = TestDataFactory.newUserStub();


        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        final var resultActions = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserRequest)))
                .andExpect(status().isCreated());


        var contentAsString  = resultActions.andReturn().getResponse().getContentAsString();
        var createUserResponse = objectMapper.readValue(contentAsString, UserItem.class);

        assertCreateUserResponse(createUserResponse);



    }

    private void assertCreateUserResponse(UserItem response) {
        assertThat(response).isNotNull();
        assertThat(response.getUserId()).isNotNull();
        assertThat(response.getName()).isEqualTo("Juan Rodriguez");
        assertThat(response.getEmail()).isEqualTo("juan@rodriguez.do");
        assertThat(response.getPassword()).isEqualTo("0123456789$abcdefgAB");
        assertThat(response.getCreated()).isNotNull();
        assertThat(response.getLastLogin()).isNotNull();
        assertThat(response.getModified()).isNotNull();
        var firstPhone = response.getPhones().get(FIRST_PHONE);
        assertPhoneItem(firstPhone);

    }

    private void assertPhoneItem(PhoneItem phoneItem) {
        assertThat(phoneItem.getCityCode()).isEqualTo("1");
        assertThat(phoneItem.getNumber()).isEqualTo("123456");
        assertThat(phoneItem.getCountryCode()).isEqualTo("52");
    }


}