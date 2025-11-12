package com.example.TintWiz.dto;

import com.example.TintWiz.entity.AssetUsage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetsDto {
    private Long idassets;
    private String imageUrl;
    private AssetUsage usedIn;
    private String imageAlt;
    private Float width;
    private Float height;
    private String extension;
}
