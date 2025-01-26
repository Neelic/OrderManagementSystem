package com.github.neelic.ordermanagementsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.neelic.ordermanagementsystem.repository.OrderRepository;
import com.github.neelic.ordermanagementsystem.repository.entity.Order;
import com.github.neelic.ordermanagementsystem.repository.entity.Product;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderServiceTest {

    private OrderRepository repository;
    private ObjectMapper mapper;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(OrderRepository.class);
        mapper = Mockito.mock(ObjectMapper.class);
        productService = Mockito.mock(ProductService.class);
    }

    @Test
    void testGetOrdersByCustomerName() {
        Order testOrder = new Order();
        testOrder.setCustomerName("customerName");
        ArrayList<Order> orders = new ArrayList<>(List.of(testOrder));
        Mockito.when(repository.findAllByCustomerName("customerName")).thenReturn(orders);

        OrderService orderService = new OrderService(repository, mapper, productService);
        List<Order> ordersByCustomerName = orderService.getOrdersByCustomerName("customerName");

        assertEquals(ordersByCustomerName, orders);
        Mockito.verify(repository).findAllByCustomerName("customerName");
    }

    @Test
    void testZeroGetOrdersByCustomerName() {
        Mockito.when(repository.findAllByCustomerName("customerName")).thenReturn(new ArrayList<>());

        OrderService orderService = new OrderService(repository, mapper, productService);
        List<Order> ordersByCustomerName = orderService.getOrdersByCustomerName("customerName");

        assertTrue(ordersByCustomerName.isEmpty());
        Mockito.verify(repository).findAllByCustomerName("customerName");
    }

    @SneakyThrows
    @Test
    void testExportOrderToJson() {
        Mockito.when(mapper.writeValueAsString(Mockito.any())).thenReturn("mockedJson");

        OrderService orderService = new OrderService(repository, mapper, productService);
        String json = orderService.exportOrderToJson(new Order());

        assertEquals("mockedJson", json);
        Mockito.verify(mapper).writeValueAsString(Mockito.any());
    }

    @SneakyThrows
    @Test
    void testFailExportOrderToJson() {
        Mockito.when(mapper.writeValueAsString(Mockito.any())).thenThrow(JsonProcessingException.class);

        OrderService orderService = new OrderService(repository, mapper, productService);
        String json = orderService.exportOrderToJson(new Order());

        assertEquals("", json);
        Mockito.verify(mapper).writeValueAsString(Mockito.any());
    }

    @Test
    void testGetAllOrders() {
        Order testOrder = new Order();
        testOrder.setCustomerName("customerName");
        ArrayList<Order> testList = new ArrayList<>(List.of(testOrder));
        Mockito.when(repository.findAll()).thenReturn(testList);

        OrderService orderService = new OrderService(repository, mapper, productService);
        List<Order> orders = orderService.getAllOrders();

        assertEquals(orders, testList);
        Mockito.verify(repository).findAll();
    }

    @Test
    void shouldCreateOrder() {
        List<UUID> ids = new ArrayList<>(List.of(UUID.randomUUID(), UUID.randomUUID()));
        Product product = new Product();
        product.setId(ids.get(0));
        product.setName("productName");
        product.setPrice(BigDecimal.valueOf(100.0));
        Mockito.when(productService.getProducts(ids)).thenReturn(new ArrayList<>(List.of(product)));

        OrderService orderService = new OrderService(repository, mapper, productService);
        orderService.createOrder(ids, "customerName");

        Mockito.verify(repository).save(Mockito.any());
    }
}