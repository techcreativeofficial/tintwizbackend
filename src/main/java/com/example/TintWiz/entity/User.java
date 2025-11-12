package com.example.TintWiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long iduser;

    private String username;
    private String password;
    private String role;
    private Boolean status;
    private Instant created;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Articles> articles;
}
