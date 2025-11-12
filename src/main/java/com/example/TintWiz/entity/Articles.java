package com.example.TintWiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "articles")
public class Articles {
    @Id
    @SequenceGenerator(name = "articles_sequence", sequenceName = "articles_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articles_sequence")
    private Long idarticles;

    @Column(name = "image_url", columnDefinition = "text")
    private String imageUrl;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
