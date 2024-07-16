package com.example.finx.user.dto;

import com.example.finx.user.entity.UserEntity;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .username(username)
                .password(password)
                .build();
    }
}
