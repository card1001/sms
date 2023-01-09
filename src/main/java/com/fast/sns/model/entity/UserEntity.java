package com.fast.sns.model.entity;

import com.fast.sns.model.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name="user")
@Getter@Setter
@SQLDelete(sql="UPDATED user SET deleted_at = NOW() where id=?")
@Where(clause = "deledted_at is NULL")
public class UserEntity {

    @Id
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;
    @Column(name = "registered_at")
    private Timestamp registeredAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @PrePersist
    void resisteredAt(){
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt(){
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public static UserEntity of(String userName, String password){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPassword(password);
        return userEntity;
    }
}
