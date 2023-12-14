package com.example.untitled13.repository;

import com.example.untitled13.entity.Product;

import com.example.untitled13.enums.AnimalType;
import com.example.untitled13.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product p " +
            "JOIN product_info pi ON p.product_info_id = pi.id " +
            "WHERE (:typeAnimal IS NULL OR pi.type_animal = CAST(:typeAnimal AS text)) " +
            "AND (:type IS NULL OR pi.type = CAST(:type AS text)) " +
            "AND (:minPrice IS NULL OR p.price >= CAST(:minPrice AS double precision)) " +
            "AND (:maxPrice IS NULL OR p.price <= CAST(:maxPrice AS double precision)) " +
            "AND p.amount > 0 " +
            "AND (:company IS NULL OR pi.company = CAST(:company AS text)) " +
            "AND (:name IS NULL OR p.name = CAST(:name AS text))",
            nativeQuery = true)
    List<Product> findByFilters(@Param("typeAnimal") String typeAnimal,
                                @Param("type") String type,
                                @Param("minPrice") double minPrice,
                                @Param("maxPrice") double maxPrice,
                                @Param("company") String company,
                                @Param("name") String name);
}