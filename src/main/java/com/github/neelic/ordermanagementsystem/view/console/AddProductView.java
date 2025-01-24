package com.github.neelic.ordermanagementsystem.view.console;

import com.github.neelic.ordermanagementsystem.view.View;
import com.github.neelic.ordermanagementsystem.view.ViewUtils;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddProductView implements View {

    public static final String NAME = "addProduct";
    private final Scanner scanner;

    @Getter
    private String productName;
    @Getter
    private BigDecimal productPrice;

    public AddProductView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String show() {
        System.out.println("Введите название продукта");
        productName = ViewUtils.getUserChoice(scanner, (choice) -> choice == null || choice.isEmpty());

        System.out.println("Введите цену продукта");
        productPrice = new BigDecimal(ViewUtils.getUserChoice(scanner, this::isNotCorrectChoice));
        return NOT_CHOICE;
    }

    private boolean isNotCorrectChoice(String choice) {
        if (choice == null || choice.isEmpty()) return true;
        BigDecimal decimal;
        try {
            decimal = new BigDecimal(choice);
        } catch (NumberFormatException e) {
            return true;
        }
        return decimal.compareTo(BigDecimal.ZERO) < 0;
    }
}
