package com.example.TintWiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {
    private Long id;
    private String oldpassword;
    private String newpassword;
}
