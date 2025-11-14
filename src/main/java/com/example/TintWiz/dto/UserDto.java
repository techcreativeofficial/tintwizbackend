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
    private String email;
    private String username;
    private String fullname;
    private String password;
    private Integer phone_number;
    private String profile_picture;
    private String role;
    private Boolean status;
    private Instant created;

}
