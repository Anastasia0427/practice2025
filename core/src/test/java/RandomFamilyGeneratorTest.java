import model.FamilyRecord;
import org.junit.jupiter.api.Test;
import utils.RandomFamilyGenerator;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RandomFamilyGeneratorTest {

    @Test
    void generateRandomFamilyNotNullTest() {
        FamilyRecord record = RandomFamilyGenerator.generateRandomFamily();
        assertNotNull(record, "Сгенерированная запись не должна быть null");
    }

    @Test
    void generateRandomFamilyFlightNumberBoundsTest() {
        FamilyRecord record = RandomFamilyGenerator.generateRandomFamily();
        int flightNumber = record.flightNumber();
        assertTrue(flightNumber >= 1 && flightNumber <= 10,
                "Номер рейса должен быть в диапазоне [1, 10], получено: " + flightNumber);
    }

    @Test
    void generateRandomFamilyAmountBoundsTest() {
        FamilyRecord record = RandomFamilyGenerator.generateRandomFamily();
        int familyAmount = record.familyAmount();
        assertTrue(familyAmount >= 1 && familyAmount <= 8,
                "Количество человек в семье должно быть в диапазоне [1, 8], получено: " + familyAmount);
    }

    @Test
    void generateRandomFamilyMultipleIterationsTest() {
        for (int i = 0; i < 1000; i++) {
            FamilyRecord record = RandomFamilyGenerator.generateRandomFamily();
            assertTrue(record.flightNumber() >= 1 && record.flightNumber() <= 10,
                    "Номер рейса вне диапазона [1, 10] на итерации " + i);
            assertTrue(record.familyAmount() >= 1 && record.familyAmount() <= 8,
                    "Количество человек вне диапазона [1, 8] на итерации " + i);
        }
    }

    @Test
    void generateRandomFamilyDistributionTest() {
        Set<Integer> flightNumbers = new HashSet<>();
        Set<Integer> familyAmounts = new HashSet<>();
        int iterations = 1000;

        for (int i = 0; i < iterations; i++) {
            FamilyRecord record = RandomFamilyGenerator.generateRandomFamily();
            flightNumbers.add(record.flightNumber());
            familyAmounts.add(record.familyAmount());
        }

        // Проверяем, что генерация охватывает хотя бы половину возможных значений
        assertTrue(flightNumbers.size() >= 5,
                "Ожидается, что сгенерировано не менее 5 различных номеров рейсов, получено: " + flightNumbers.size());
        assertTrue(familyAmounts.size() >= 4,
                "Ожидается, что сгенерировано не менее 4 различных размеров семей, получено: " + familyAmounts.size());
    }
}