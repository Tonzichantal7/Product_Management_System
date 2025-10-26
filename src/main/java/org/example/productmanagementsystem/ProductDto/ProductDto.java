package org.example.productmanagementsystem.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ProductDto {
    private int id;
    private String name;
    private String description;
    private double price;
}
