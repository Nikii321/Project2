package com.example.untitled13.controller;

import com.example.untitled13.entity.Product;
import com.example.untitled13.service.FilesStorageService;
import com.example.untitled13.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FilesStorageService filesStorageService;

    @Autowired
    private ProductService productService;

    @PostMapping("/{productId}")
    public String saveFile(@RequestParam("file")MultipartFile file, @PathVariable("productId") Long id){
        Product product = productService.findProductById(id).orElseThrow(()-> new RuntimeException("Not found product"));
        String name = filesStorageService.save(file);
        product.getProductInfo().setFilePath(name, file.getContentType());
        productService.saveProduct(product);
        return "file upload";
    }

    @DeleteMapping("/{productId}")
    public String deleteMapping(@PathVariable("productId") Long id){
        Product product = productService.findProductById(id).orElseThrow(()-> new RuntimeException("Not found product"));
        filesStorageService.delete(product.getProductInfo().getFilePath());
        product.getProductInfo().setFilePath(null, null);
        productService.saveProduct(product);
        return "file deleted";
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Resource> findByProduct(@PathVariable("productId") Long id){
        Product product = productService.findProductById(id).orElseThrow(()-> new RuntimeException("Not found product"));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(product.getProductInfo().getContentType())).body(filesStorageService.load(product.getProductInfo().getFilePath()));
    }
}
