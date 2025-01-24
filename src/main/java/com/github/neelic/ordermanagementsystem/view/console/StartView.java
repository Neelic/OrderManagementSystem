package com.github.neelic.ordermanagementsystem.view.console;

import com.github.neelic.ordermanagementsystem.view.View;
import com.github.neelic.ordermanagementsystem.view.ViewUtils;

import java.util.Scanner;

public class StartView implements View {

    public static final String NAME = "start";

    public static final String ADD_PRODUCT = "1";
    public static final String CREATE_ORDER = "2";
    public static final String GET_ORDERS_BY_CUSTOMER_NAME = "3";
    public static final String EXPORT_ORDERS_TO_JSON = "4";
    public static final String EXIT = "5";
    private static final String MESSAGE = String.format("""
            Выберите действие:
            %s - Добавить продукт
            %s - Создать заказ
            %s - Вывести заказы по имени клиента
            %s - Экспортировать заказы в JSON
            %s - Выход
            """, ADD_PRODUCT, CREATE_ORDER, GET_ORDERS_BY_CUSTOMER_NAME, EXPORT_ORDERS_TO_JSON, EXIT);

    private final Scanner scanner;

    public StartView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String show() {
        System.out.println(MESSAGE);
        return ViewUtils.getUserChoice(scanner, this::isNotCorrectChoice);
    }

    private boolean isNotCorrectChoice(String choice) {
        return !choice.equals(ADD_PRODUCT) && !choice.equals(CREATE_ORDER) && !choice.equals(GET_ORDERS_BY_CUSTOMER_NAME)
                && !choice.equals(EXPORT_ORDERS_TO_JSON) && !choice.equals(EXIT);
    }
}
