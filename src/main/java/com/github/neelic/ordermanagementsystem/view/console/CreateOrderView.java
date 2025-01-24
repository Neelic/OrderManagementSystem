package com.github.neelic.ordermanagementsystem.view.console;

import com.github.neelic.ordermanagementsystem.view.View;
import com.github.neelic.ordermanagementsystem.view.ViewUtils;

import java.util.Scanner;

public class CreateOrderView implements View {

    public static String NAME = "createOrder";

    private final Scanner scanner;

    public CreateOrderView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String show() {
        System.out.println("Введите имя клиента");
        return ViewUtils.getUserChoice(scanner, (choice) -> choice == null || choice.isEmpty());
    }
}
