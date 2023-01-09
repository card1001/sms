package com.fast.sns.controller.response;

import com.fast.sns.controller.request.UserJoinRequest;
import com.fast.sns.model.User;
import com.fast.sns.model.UserRole;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserJoinResponse {
    private Integer id;
    private String userName;
    private UserRole userRole;

    public static UserJoinResponse fromUser(User user){
        return new UserJoinResponse(
                user.getId(),
                user.getUserName(),
                user.getUserRole()
        );
    }
}
