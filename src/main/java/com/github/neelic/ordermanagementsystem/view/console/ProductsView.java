package com.github.neelic.ordermanagementsystem.view.console;

import com.github.neelic.ordermanagementsystem.repository.entity.Product;
import com.github.neelic.ordermanagementsystem.view.View;
import com.github.neelic.ordermanagementsystem.view.ViewUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ProductsView implements View {

    public static final String NAME = "showProducts";
    public static final String CONFIRM_CHOICE = "0";
    private static final String CHOOSE_PRODUCT_MESSAGE = String.format("""
            Введите %s, чтобы подтвердить выбор продуктов
            Выберите продукт:
            """, CONFIRM_CHOICE);

    private final Scanner scanner;
    @Getter
    private final List<UUID> idProducts = new ArrayList<>();
    @Setter
    @Getter
    private List<Product> products;
    @Setter
    private boolean isChooseProducts = true;

    public ProductsView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String show() {
        if (products == null || products.isEmpty()) {
            System.out.println("Продукты не найдены");
            return NOT_CHOICE;
        }

        System.out.println(CHOOSE_PRODUCT_MESSAGE);
        System.out.println("Доступные продукты:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%d. Name: %s, Price: %s%n", i + 1,
                    product.getName(), product.getPrice());
        }

        if (!isChooseProducts) {
            return NOT_CHOICE;
        }

        String choice;
        do {
            choice = ViewUtils.getUserChoice(scanner, this::isNotCorrectChoice);

            if (!CONFIRM_CHOICE.equals(choice)) {
                idProducts.add(products.get(Integer.parseInt(choice) - 1).getId());
            } else {
                System.out.printf("Выберете еще 1 продукт или нажмите %s, чтобы продолжить%n", CONFIRM_CHOICE);
            }
        } while (!CONFIRM_CHOICE.equals(choice));

        return NOT_CHOICE;
    }

    private boolean isNotCorrectChoice(String choice) {
        if (choice == null || choice.isEmpty()) {
            return true;
        }

        if (CONFIRM_CHOICE.equals(choice)) {
            return false;
        }

        try {
            int productNumber = Integer.parseInt(choice);

            if (productNumber < 1 || productNumber > products.size()) {
                return true;
            }
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }
}
