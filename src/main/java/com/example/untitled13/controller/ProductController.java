package com.example.untitled13.controller;

import com.example.untitled13.entity.Product;
import com.example.untitled13.enums.AnimalType;
import com.example.untitled13.enums.ProductType;
import com.example.untitled13.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> getProductsByFilters(
            @RequestParam(name = "typeAnimal", required = false) String animalType,
            @RequestParam(name = "productType",required = false) String productType,
            @RequestParam(name = "minPrice",required = false) Double minPrice,
            @RequestParam(name = "maxPrice",required = false) Double maxPrice,
            @RequestParam(name = "company",required = false) String company,
            @RequestParam(name = "name", required = false) String name) {

        List<Product> products = productService.findByFilters(animalType, productType, minPrice, maxPrice, company, name);
        return ResponseEntity.ok(products);
    }
}
