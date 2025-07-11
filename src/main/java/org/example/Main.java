package org.example;

/*
 * Белых Анастасия Сергеевна, 3 к. 91 гр.
 * В аэропорту решено собрать данные о 10 рейсах, датируемых определенным днем.
 * Для каждого рейса вводятся следующие данные о каждой улетающей этим рейсом семье: номер рейса
 * (числа от 1 до 10)
 * и число членов семьи (подумайте, как можно смоделировать ввод с помощью случайных чисел).
 *
 *
 * Число пассажиров на каждый рейс неизвестно, поэтому ввод данных продолжается,
 * пока число членов семьи не равно 0.
 * Напишите класс, который принимает с клавиатуры данные, подсчитывает
 * и выводит на экран число пассажиров на каждом из 10 рейсов.
 *
 * В задаче должны использоваться коллекции (за исключением Map),
 * необходимо выбрать наиболее подходящую (подходящие) коллекцию для этой задачи, аргументировать свой выбор
 *
 * В задаче должны использоваться элементы функционального программирования
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final int RECORDS_COUNT = 25;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите способ ввода данных: ");
        System.out.println("1. Случайная генерация");
        System.out.println("2. Ручной ввод");
        System.out.print("    —> ");

        int choice = scanner.nextInt();
        Flight flightData;

        if (choice == 1) {
            flightData = new Flight(RECORDS_COUNT);
        } else if (choice == 2) {
            ArrayList<FamilyRecord> records = new ArrayList<>();
            System.out.println("Вводите данные о семьях (номер рейса от 1 до 10," +
                    " количество членов семьи)");
            System.out.println("Для завершения ввода введите количество членов семьи, равное 0");

            while (true) {
                System.out.print("Номер рейса: ");
                int flightNumber = scanner.nextInt();

                System.out.print("Количество членов семьи: ");
                int familyAmount = scanner.nextInt();

                if (familyAmount == 0) {
                    break;
                }

                if (flightNumber < 1 || flightNumber > 10) {
                    System.out.println("Номер рейса должен быть от 1 до 10. Повторите ввод");
                    continue;
                }

                if (familyAmount < 0) {
                    System.out.println("Количество членов семьи не может быть отрицательным! Повторите ввод");
                    continue;
                }

                records.add(new FamilyRecord(flightNumber, familyAmount));
            }

            flightData = new Flight(records);
        } else {
            System.out.println("Неверный выбор! Программа завершена");
            scanner.close();
            return;
        }

        FlightProcessing.countPeopleByFlight(flightData.getRecords());
        scanner.close();;
    }
}