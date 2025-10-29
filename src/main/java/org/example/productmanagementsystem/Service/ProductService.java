package org.example.productmanagementsystem.Service;

import org.example.productmanagementsystem.Model.Product;
import org.example.productmanagementsystem.ProductDto.ProductDto;

import java.util.List;

public interface ProductService {

    Boolean deleteProduct(Long id);

    Boolean saveProduct(ProductDto productDto);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product patch(Long id, Product product);
}
