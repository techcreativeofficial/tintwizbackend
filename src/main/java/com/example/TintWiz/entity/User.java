package com.example.TintWiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "userr_sequence",sequenceName = "userr_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userr_sequence")
    private Long iduser;
    private String kodeuser;
    private String username;
    private String password;
    private String role;
    private Boolean status;
    private Instant created;


}
