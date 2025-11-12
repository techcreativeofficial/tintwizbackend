package com.example.TintWiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long idproduct;
    private String imageUrl;
    private String type;
    private String title;
    private String description;
    private Float rating;
    private Integer stock;
}
