package org.example.productmanagementsystem.Controller;

import org.example.productmanagementsystem.Model.Product;
import org.example.productmanagementsystem.ProductDto.ProductDto;
import org.example.productmanagementsystem.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // GET product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // POST - create new product
    @PostMapping("/addProduct")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Product created successfully!");
    }

    // PATCH - update product partially
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.patch(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }
}
