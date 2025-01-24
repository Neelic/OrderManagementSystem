package com.github.neelic.ordermanagementsystem;

import com.github.neelic.ordermanagementsystem.controller.OrderManagementController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.github.neelic.ordermanagementsystem.*"
})
public class OrderManagementSystemApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(OrderManagementSystemApplication.class, args);
        ctx.getBean(OrderManagementController.class).start();
    }

}
