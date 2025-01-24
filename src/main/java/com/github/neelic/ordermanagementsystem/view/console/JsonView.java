package com.github.neelic.ordermanagementsystem.view.console;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.neelic.ordermanagementsystem.repository.entity.Order;
import com.github.neelic.ordermanagementsystem.view.View;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JsonView implements View {

    public static final String NAME = "jsonView";
    private final ObjectMapper mapper;
    @Getter
    @Setter
    private List<Order> orders;

    public JsonView(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String show() {
        if (orders == null || orders.isEmpty() || mapper == null) {
            return NOT_CHOICE;
        }

        try {
            System.out.println(mapper.writeValueAsString(orders));
        } catch (JsonProcessingException e) {
            System.out.println("Не удалось преобразовать в JSON: " + e.getMessage());
        }

        return NOT_CHOICE;
    }
}
