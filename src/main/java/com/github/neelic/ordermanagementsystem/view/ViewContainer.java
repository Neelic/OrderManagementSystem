package com.github.neelic.ordermanagementsystem.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.neelic.ordermanagementsystem.view.console.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class ViewContainer {

    private final HashMap<String, View> views;

    public ViewContainer(Scanner scanner, ObjectMapper mapper) {
        views = new HashMap<>(
                Map.of(
                        StartView.NAME, new StartView(scanner),
                        ProductsView.NAME, new ProductsView(scanner),
                        CreateOrderView.NAME, new CreateOrderView(scanner),
                        OrdersView.NAME, new OrdersView(),
                        JsonView.NAME, new JsonView(mapper),
                        AddProductView.NAME, new AddProductView(scanner),
                        GetFilterParameterView.NAME, new GetFilterParameterView(scanner)
                )
        );
    }

    public View findView(String name) {
        return views.get(name);
    }
}
