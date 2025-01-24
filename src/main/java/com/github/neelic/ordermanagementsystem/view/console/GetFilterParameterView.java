package com.github.neelic.ordermanagementsystem.view.console;

import com.github.neelic.ordermanagementsystem.view.View;
import com.github.neelic.ordermanagementsystem.view.ViewUtils;

import java.util.Scanner;

public class GetFilterParameterView implements View {

    public static final String NAME = "getFilterParameter";

    private final Scanner scanner;

    public GetFilterParameterView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String show() {
        System.out.println("Введите имя клиента");
        return ViewUtils.getUserChoice(scanner, (choice) -> choice == null || choice.isEmpty());
    }
}
