package com.example.TintWiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long iduser;
    private String username;
    private String password;
    private String role;
    private Boolean status;
    private Instant created;

}
