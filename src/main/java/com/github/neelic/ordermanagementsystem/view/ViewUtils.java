package com.github.neelic.ordermanagementsystem.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

public class ViewUtils {

    public static String getUserChoice(Scanner scanner, Predicate<String> isNotCorrectChoice) {
        String choice = View.NOT_CHOICE;

        do {
            try {
                choice = scanner.nextLine().strip();

                if (isNotCorrectChoice.test(choice)) {
                    System.out.println("Некорректный ввод");
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите число");
                scanner.next();
            }
        } while (isNotCorrectChoice.test(choice));

        return choice;
    }
}
