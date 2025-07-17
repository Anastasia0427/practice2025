package utils;

import model.FamilyRecord;
import java.util.Random;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RandomFamilyGenerator {
    private static final Logger logger = LogManager.getLogger(RandomFamilyGenerator.class);
    private static final int FLIGHT_MIN = 1;
    private static final int FLIGHT_MAX = 11;
    private static final int FAMILY_MIN = 1;
    private static final int FAMILY_MAX = 9;

    public static FamilyRecord generateRandomFamily() {
        Random random = new Random();
        int flightNumber = random.nextInt(FLIGHT_MIN, FLIGHT_MAX);
        int familyAmount = random.nextInt(FAMILY_MIN, FAMILY_MAX);

        logger.debug("Сгенерирована случайная семья: рейс " + flightNumber
                + ", " + familyAmount + " человек");

        return new FamilyRecord(flightNumber, familyAmount);
    }
}