package com.github.neelic.ordermanagementsystem.repository;

import com.github.neelic.ordermanagementsystem.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * {@link ProductRepository} for {@link Product} entity
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
