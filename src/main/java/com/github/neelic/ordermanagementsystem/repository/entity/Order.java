package com.github.neelic.ordermanagementsystem.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<Product> products;

    public void addProduct(Product product) {
        if (products == null) {
            products = List.of(product);
        } else {
            products.add(product);
        }
    }

    public void removeProduct(Product product) {
        if (products == null) {
            return;
        }

        products.remove(product);
    }

    //    @PrePersist
//    @PreUpdate
    public void calculateTotalPrice() throws SQLIntegrityConstraintViolationException {
        if (products == null || products.isEmpty()) {
            throw new SQLIntegrityConstraintViolationException("Order must have at least one product");
        }

        totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
