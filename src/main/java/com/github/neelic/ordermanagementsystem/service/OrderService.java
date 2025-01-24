package com.github.neelic.ordermanagementsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.neelic.ordermanagementsystem.repository.OrderRepository;
import com.github.neelic.ordermanagementsystem.repository.entity.Order;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ObjectMapper objectMapper, ProductService productService) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
        this.productService = productService;
    }

    public void createOrder(List<UUID> idProducts, String customerName) {
        try {
            Order order = new Order();
            order.setCustomerName(customerName);
            order.setProducts(productService.getProducts(idProducts));
            order.calculateTotalPrice();
            orderRepository.save(order);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Order> getOrdersByCustomerName(String customerName) {
        return orderRepository.findAllByCustomerName(customerName);
    }

    public String exportOrderToJson(Order order) {
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
            return "";
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
