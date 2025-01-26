package com.github.neelic.ordermanagementsystem.service;

import com.github.neelic.ordermanagementsystem.repository.ProductRepository;
import com.github.neelic.ordermanagementsystem.repository.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
    }

    @Test
    void getProducts() {
        ProductService productService = new ProductService(productRepository);
        Product product = new Product();
        product.setName("productName");
        product.setPrice(BigDecimal.valueOf(100.0));
        product.setId(UUID.randomUUID());
        ArrayList<Product> testProducts = new ArrayList<>(List.of(product));
        Mockito.when(productRepository.findAllById(Mockito.any())).thenReturn(testProducts);

        List<Product> products = productService.getProducts(new ArrayList<>(List.of(product.getId())));

        assertEquals(testProducts, products);
        Mockito.verify(productRepository).findAllById(Mockito.any());
    }

    @Test
    void getAllProducts() {
        ProductService productService = new ProductService(productRepository);
        Product product = new Product();
        product.setName("productName");
        product.setPrice(BigDecimal.valueOf(100.0));
        product.setId(UUID.randomUUID());
        ArrayList<Product> testProducts = new ArrayList<>(List.of(product));
        Mockito.when(productRepository.findAll()).thenReturn(testProducts);

        List<Product> products = productService.getAllProducts();

        assertEquals(testProducts, products);
        Mockito.verify(productRepository).findAll();
    }

    @Test
    void shouldAddProduct() {
        ProductService productService = new ProductService(productRepository);

        productService.addProduct("productName", BigDecimal.valueOf(100.0));

        Mockito.verify(productRepository).save(Mockito.any());
    }
}