package com.fast.sns.controller.response;

import com.fast.sns.controller.request.UserJoinRequest;
import com.fast.sns.model.User;
import com.fast.sns.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponse {
    private String token;
}