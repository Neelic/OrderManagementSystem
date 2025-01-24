package com.github.neelic.ordermanagementsystem.view.console;

import com.github.neelic.ordermanagementsystem.repository.entity.Order;
import com.github.neelic.ordermanagementsystem.view.View;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrdersView implements View {

    public static final String NAME = "showOrders";

    private List<Order> orders;

    @Override
    public String show() {
        if (orders == null || orders.isEmpty()) {
            return NOT_CHOICE;
        }

        orders.forEach(order -> System.out.println("Customer name: " + order.getCustomerName() +
                ", Total price: " + order.getTotalPrice()));
        return NOT_CHOICE;
    }
}
