package com.example.TintWiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assets")
public class Assets {
    @Id
    @SequenceGenerator(name = "assets_sequence", sequenceName = "assets_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assets_sequence")
    private Long idassets;

    @Column(name = "image_url", columnDefinition = "text")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "used_in", nullable = false)
    private AssetUsage usedIn;

    @Column(name = "image_alt", columnDefinition = "text")
    private String imageAlt;

    private Float width;
    private Float height;
    private String extension;
}
