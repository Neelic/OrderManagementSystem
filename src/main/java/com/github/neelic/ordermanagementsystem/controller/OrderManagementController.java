package com.github.neelic.ordermanagementsystem.controller;

import com.github.neelic.ordermanagementsystem.repository.entity.Order;
import com.github.neelic.ordermanagementsystem.service.OrderService;
import com.github.neelic.ordermanagementsystem.service.ProductService;
import com.github.neelic.ordermanagementsystem.view.ViewContainer;
import com.github.neelic.ordermanagementsystem.view.console.*;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
public class OrderManagementController {

    private final ViewContainer viewContainer;
    private final OrderService orderService;
    private final ProductService productService;

    public OrderManagementController(ViewContainer viewContainer, OrderService orderService, ProductService productService) {
        this.viewContainer = viewContainer;
        this.orderService = orderService;
        this.productService = productService;
    }

    public void start() {
        String choice = viewContainer.findView(StartView.NAME).show();

        switch (choice) {
            case StartView.ADD_PRODUCT -> addProduct();
            case StartView.CREATE_ORDER -> createOrder();
            case StartView.GET_ORDERS_BY_CUSTOMER_NAME -> getOrdersByCustomerName();
            case StartView.EXPORT_ORDERS_TO_JSON -> exportOrdersToJson();
            case StartView.EXIT -> {
            }
        }
    }

    public void createOrder() {
        ProductsView showProducts = (ProductsView) viewContainer.findView(ProductsView.NAME);
        showProducts.setProducts(productService.getAllProducts());
        showProducts.show();
        List<UUID> idProducts = showProducts.getIdProducts();

        String customerName = viewContainer.findView(CreateOrderView.NAME).show();
        orderService.createOrder(idProducts, customerName);
        start();
    }

    private void exportOrdersToJson() {
        List<Order> orders = orderService.getAllOrders();
        JsonView view = (JsonView) viewContainer.findView(JsonView.NAME);
        view.setOrders(orders);
        view.show();
        start();
    }

    private void getOrdersByCustomerName() {
        String customerName = viewContainer.findView(CreateOrderView.NAME).show();
        List<Order> orders = orderService.getOrdersByCustomerName(customerName);
        OrdersView view = (OrdersView) viewContainer.findView(OrdersView.NAME);
        view.setOrders(orders);
        view.show();
        start();
    }

    private void addProduct() {
        AddProductView view = (AddProductView) viewContainer.findView(AddProductView.NAME);
        view.show();
        String productName = view.getProductName();
        BigDecimal productPrice = view.getProductPrice();
        productService.addProduct(productName, productPrice);
        start();
    }
}
