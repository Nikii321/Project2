package com.example.untitled13.service;

import com.example.untitled13.entity.Product;
import com.example.untitled13.enums.AnimalType;
import com.example.untitled13.enums.ProductType;
import com.example.untitled13.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByFilters(
            String animalType,
            String productType,
            Double minPrice,
            Double maxPrice,
            String company,
            String name
    ) {
        double min = (minPrice != null) ? minPrice : 0D;
        double max = (maxPrice != null) ? maxPrice : Double.MAX_VALUE;

        return productRepository.findByFilters(animalType, productType, min, max, company, name);
    }

}
