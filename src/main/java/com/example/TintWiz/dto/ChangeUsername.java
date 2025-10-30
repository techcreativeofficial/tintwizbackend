package com.example.TintWiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUsername {
    private Long id;
    private String oldusername;
    private String newusername;
}
