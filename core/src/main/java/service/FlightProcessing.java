package service;

import model.FamilyRecord;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FlightProcessing {
    private static final Logger logger = LogManager.getLogger(FlightProcessing.class);

    public static void countPeopleByFlight(List<FamilyRecord> records) {
        logger.info("Начинаем подсчет пассажиров по рейсам. Всего записей: " + records.size());

        records.stream()
                .map(FamilyRecord::flightNumber)
                .distinct()
                .sorted()
                .forEach(flightNumber -> {
                    int count = records.stream()
                            .filter(record -> record .flightNumber() == flightNumber)
                            .mapToInt(FamilyRecord::familyAmount)
                            .sum();
                    if (count == 0) {
                        logger.warn("Для рейса " + flightNumber + " не найдено данных");
                        System.out.println("flight " + flightNumber + ": no data found");
                    } else {
                        logger.info("Рейс " + flightNumber + ": " + count + " пассажиров");
                        System.out.println("flight " + flightNumber + ": " + count + " passengers");
                    }
                });

        logger.info("Подсчет пассажиров по рейсам завершен");
    }

}