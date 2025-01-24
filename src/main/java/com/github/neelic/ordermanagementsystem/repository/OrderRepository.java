package com.github.neelic.ordermanagementsystem.repository;

import com.github.neelic.ordermanagementsystem.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * {@link OrderRepository} for {@link Order} entity
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByCustomerName(String customerName);
}
