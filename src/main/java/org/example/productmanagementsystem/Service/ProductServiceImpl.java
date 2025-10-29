package org.example.productmanagementsystem.Service;

import org.example.productmanagementsystem.Exception.ProductAlreadyExistsException;
import org.example.productmanagementsystem.Exception.ProductNotFoundException;
import org.example.productmanagementsystem.Model.Product;
import org.example.productmanagementsystem.ProductDto.ProductDto;
import org.example.productmanagementsystem.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Boolean saveProduct(ProductDto productDto) {
        // Check if product with same name already exists
        productRepository.findByName(productDto.getName())
                .ifPresent(p -> { throw new ProductAlreadyExistsException(
                        "Product with name '" + productDto.getName() + "' already exists"); });

        // Map ProductDto to Product entity
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
//        product.setCategory(productDto.getCategory());

        productRepository.save(product);
        return true;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " does not exist"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product patch(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " does not exist"));

        if (product.getName() != null) existingProduct.setName(product.getName());
        if (product.getDescription() != null) existingProduct.setDescription(product.getDescription());
        if (product.getPrice() != 0) existingProduct.setPrice(product.getPrice());
        if (product.getQuantity() != 0) existingProduct.setQuantity(product.getQuantity());
        if (product.getCategory() != null) existingProduct.setCategory(product.getCategory());

        return productRepository.save(existingProduct);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " does not exist"));
        productRepository.delete(product);
        return true;
    }
}
