package com.asantos.demo_park_api.web.dto.mapper;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String username;
    private String role;

    public void setRole(String role_) {
        role = role_;
    }
}
