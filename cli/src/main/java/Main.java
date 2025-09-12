/*
 * Белых Анастасия Сергеевна, 3 к. 91 гр.
 * В аэропорту решено собрать данные о 10 рейсах, датируемых определенным днем.
 * Для каждого рейса вводятся следующие данные о каждой улетающей этим рейсом семье: номер рейса
 * (числа от 1 до 10)
 * и число членов семьи (подумайте, как можно смоделировать ввод с помощью случайных чисел).
 *
 * Число пассажиров на каждый рейс неизвестно, поэтому ввод данных продолжается,
 * пока число членов семьи не равно 0.
 * Напишите класс, который принимает с клавиатуры данные, подсчитывает
 * и выводит на экран число пассажиров на каждом из 10 рейсов.
 *
 * В задаче должны использоваться коллекции (за исключением Map)
 * и элементы функционального программирования
 */


import model.Flight;
import service.FlightProcessing;
import utils.ConsoleMenu;

import java.util.Scanner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Приложение запущено");
        Scanner scanner = new Scanner(System.in);

        ConsoleMenu menu = new ConsoleMenu(scanner, msg -> logger.info(msg));
        Flight flightData = menu.selectInputMethod();

        if (flightData != null) {
            logger.info("Начинаем обработку данных о рейсах");
            FlightProcessing.countPeopleByFlight(flightData.getRecords());
        }

        logger.info("Приложение завершено");
        scanner.close();
    }
}

