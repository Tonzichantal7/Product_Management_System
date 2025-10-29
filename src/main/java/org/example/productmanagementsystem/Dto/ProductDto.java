package org.example.productmanagementsystem.Dto;

import lombok.Data;

@Data

public class ProductDto {
    private Long id;

    private String name;
    private String description;
    private double price;
    private int quantity;
//    private String category;
}
