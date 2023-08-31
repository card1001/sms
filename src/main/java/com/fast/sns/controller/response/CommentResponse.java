package com.fast.sns.controller.response;

import com.fast.sns.model.Comment;
import com.fast.sns.model.Post;
import com.fast.sns.model.User;
import com.fast.sns.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private Integer id;
    private String comment;
    private Integer userId;
    private String userName;
    private Integer postId;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp removedAt;

    public static CommentResponse fromComment(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getComment(),
                comment.getUserId(),
                comment.getUserName(),
                comment.getPostId(),
                comment.getRegisteredAt(),
                comment.getUpdatedAt(),
                comment.getRemovedAt()
        );
    }
}