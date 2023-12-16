package com.example.untitled13.entity;

import com.example.untitled13.enums.AnimalType;
import com.example.untitled13.enums.ProductType;
import javax.persistence.*;

@Entity
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private String filePath;

    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath, String contentType) {
        this.filePath = filePath;
        this.contentType = contentType;
    }

    private String description;

    @Enumerated(EnumType.STRING)
    private AnimalType typeAnimal;

    private String company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AnimalType getTypeAnimal() {
        return typeAnimal;
    }

    public void setTypeAnimal(AnimalType typeAnimal) {
        this.typeAnimal = typeAnimal;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
