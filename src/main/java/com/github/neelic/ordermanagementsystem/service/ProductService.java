package com.github.neelic.ordermanagementsystem.service;

import com.github.neelic.ordermanagementsystem.repository.ProductRepository;
import com.github.neelic.ordermanagementsystem.repository.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(String productName, BigDecimal productPrice) {
        Product product = new Product();
        product.setName(productName);
        product.setPrice(productPrice);
        productRepository.save(product);
    }

    public List<Product> getProducts(List<UUID> ids) {
        return productRepository.findAllById(ids);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
