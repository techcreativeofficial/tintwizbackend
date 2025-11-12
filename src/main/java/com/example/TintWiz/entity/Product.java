package com.example.TintWiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long idproduct;

    @Column(name = "image_url", columnDefinition = "text")
    private String imageUrl;

    private String type;
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private Float rating;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
