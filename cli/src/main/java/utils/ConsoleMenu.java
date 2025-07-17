package utils;

import model.FamilyRecord;
import model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleMenu {
    private final Scanner scanner;
    private final Consumer<String> logger;

    public ConsoleMenu(Scanner scanner, Consumer<String> logger) {
        this.scanner = scanner;
        this.logger = logger;
    }

    public Flight selectInputMethod() {
        System.out.println("Выберите способ ввода данных: ");
        System.out.println("1. Случайная генерация");
        System.out.println("2. Ручной ввод");
        System.out.print("    —> ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            logger.accept("Выбран способ: случайная генерация данных");
            return new Flight(25); // RECORDS_COUNT можно передавать параметром
        } else if (choice == 2) {
            logger.accept("Выбран способ: ручной ввод данных");
            return handleManualInput();
        } else {
            logger.accept("Выбран неверный способ ввода данных: " + choice);
            System.out.println("Неверный выбор! Программа завершена");
            return null;
        }
    }

    private Flight handleManualInput() {
        List<FamilyRecord> records = new ArrayList<>();
        System.out.println("Вводите данные о семьях (номер рейса от 1 до 10, количество членов семьи)");
        System.out.println("Для завершения ввода введите количество членов семьи, равное 0");

        while (true) {
            System.out.print("Номер рейса: ");
            int flightNumber = scanner.nextInt();

            System.out.print("Количество членов семьи: ");
            int familyAmount = scanner.nextInt();

            if (familyAmount == 0) break;

            if (flightNumber < 1 || flightNumber > 10) {
                logger.accept("Введен некорректный номер рейса: " + flightNumber);
                System.out.println("Номер рейса должен быть от 1 до 10. Повторите ввод");
                continue;
            }

            if (familyAmount < 0) {
                logger.accept("Введено некорректное количество членов семьи: " + familyAmount);
                System.out.println("Количество членов семьи не может быть отрицательным! " +
                        "Повторите ввод");
                continue;
            }

            records.add(new FamilyRecord(flightNumber, familyAmount));
            logger.accept("Добавлена запись: рейс " + flightNumber + ", семья из "
                    + familyAmount + " человек");
        }

        return new Flight((ArrayList<FamilyRecord>) records);
    }
}