package com.fast.sns.controller;

import com.fast.sns.controller.request.UserJoinRequest;
import com.fast.sns.controller.request.UserLoginRequest;
import com.fast.sns.exception.ErrorCode;
import com.fast.sns.exception.SnsApplicationException;
import com.fast.sns.model.User;
import com.fast.sns.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserContollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void 회원가입() throws Exception {
        String userName = "userName";
        String password = "password";

        //TODO : mocking

        when(userService.join(userName, password)).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/vi/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                //TODO
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
            ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입시_이미_회원가딥된_userName으로_회원강비을_하는경우_오류반환() throws Exception {
        String userName = "userName";
        String password = "password";

        //TODO : mocking
        when(userService.join(userName, password)).thenThrow(new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, ""));

        ResultActions resultActions = mockMvc.perform(post("/api/vi/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        //TODO
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인() throws Exception {
        String userName = "userName";
        String password = "password";

        //TODO : mocking

        when(userService.login(userName, password)).thenReturn("test_token");

        mockMvc.perform(post("/api/vi/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        //TODO
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인시_회원가입이_안된_userName으로_입력하는경우_오류반환() throws Exception {
        String userName = "userName";
        String password = "password";

        //TODO : mocking
        when(userService.login(userName, password)).thenThrow(new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, ""));

        ResultActions resultActions = mockMvc.perform(post("/api/vi/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        //TODO
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password)))
                ).andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    public void 로그인시_틀린_password를_입력하는경우_오류반환() throws Exception {
        String userName = "userName";
        String password = "password";

        //TODO : mocking
        when(userService.login(userName, password)).thenThrow(new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, ""));

        ResultActions resultActions = mockMvc.perform(post("/api/vi/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        //TODO
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password)))
                ).andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
