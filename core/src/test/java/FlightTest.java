import model.FamilyRecord;
import model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.RandomFamilyGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightTest {
    private Flight flight;
    private FamilyRecord testRecord1;
    private FamilyRecord testRecord2;
    private ArrayList<FamilyRecord> testRecords;

    @BeforeEach
    void setUp() {
        testRecord1 = new FamilyRecord(1, 2);
        testRecord2 = new FamilyRecord(2, 4);
        testRecords = new ArrayList<>();
        testRecords.add(testRecord1);
        testRecords.add(testRecord2);
        flight = new Flight(new ArrayList<>());
    }

    @Test
    void constructWRecordsTest() {
        Flight flightWithRecords = new Flight(testRecords);
        assertEquals(2, flightWithRecords.getRecords().size(),
                "Конструктор должен корректно инициализировать список записей");
        assertTrue(flightWithRecords.getRecords().contains(testRecord1),
                "Список должен содержать первую тестовую запись");
        assertTrue(flightWithRecords.getRecords().contains(testRecord2),
                "Список должен содержать вторую тестовую запись");
    }

    @Test
    void constructWEmptyRecordsTest() {
        Flight emptyFlight = new Flight(new ArrayList<>());
        assertEquals(0, emptyFlight.getRecords().size(),
                "Конструктор должен инициализировать пустой список записей");
    }

    @Test
    void addRecordTest() {
        flight.addRecord(testRecord1);
        assertEquals(1, flight.getRecords().size(),
                "После добавления одной записи размер списка должен быть 1");
        assertTrue(flight.getRecords().contains(testRecord1),
                "Список должен содержать добавленную запись");
    }

    @Test
    void addMultipleRecordsTest() {
        flight.addRecord(testRecord1);
        flight.addRecord(testRecord2);
        assertEquals(2, flight.getRecords().size(),
                "После добавления двух записей размер списка должен быть 2");
        assertTrue(flight.getRecords().contains(testRecord1),
                "Список должен содержать первую запись");
        assertTrue(flight.getRecords().contains(testRecord2),
                "Список должен содержать вторую запись");
    }

    @Test
    void removeRecordTest() {
        flight = new Flight(testRecords);
        boolean removed = flight.removeRecord(testRecord1);
        assertTrue(removed, "Метод removeRecord должен вернуть true при успешном удалении");
        assertEquals(1, flight.getRecords().size(),
                "После удаления одной записи размер списка должен быть 1");
        assertFalse(flight.getRecords().contains(testRecord1),
                "Удаленная запись не должна присутствовать в списке");
        assertTrue(flight.getRecords().contains(testRecord2),
                "Оставшаяся запись должна присутствовать в списке");
    }

    @Test
    void removeNonExistentRecordTest() {
        flight = new Flight(testRecords);
        FamilyRecord nonExistentRecord = new FamilyRecord(3, 5);
        boolean removed = flight.removeRecord(nonExistentRecord);
        assertFalse(removed,
                "Метод removeRecord должен вернуть false для несуществующей записи");
        assertEquals(2, flight.getRecords().size(),
                "Размер списка не должен измениться при попытке удаления несуществующей записи");
    }

    @Test
    void removeRecordByIndexTest() {
        flight = new Flight(testRecords);
        flight.removeRecordByIndex(0);
        assertEquals(1, flight.getRecords().size(),
                "После удаления записи по индексу размер списка должен быть 1");
        assertFalse(flight.getRecords().contains(testRecord1),
                "Запись с индексом 0 должна быть удалена");
        assertTrue(flight.getRecords().contains(testRecord2),
                "Оставшаяся запись должна присутствовать в списке");
    }

    @Test
    void removeRecordByInvalidIndexTest() {
        flight = new Flight(testRecords);
        assertThrows(IndexOutOfBoundsException.class, () -> flight.removeRecordByIndex(10),
                "Должно выброситься исключение при удалении по невалидному индексу");
        assertEquals(2, flight.getRecords().size(),
                "Размер списка не должен измениться при попытке удаления по невалидному индексу");
    }

    @Test
    void containsRecordTest() {
        flight.addRecord(testRecord1);
        assertTrue(flight.containsRecord(testRecord1),
                "Метод containsRecord должен вернуть true для существующей записи");
        assertFalse(flight.containsRecord(testRecord2),
                "Метод containsRecord должен вернуть false для несуществующей записи");
    }

    @Test
    void containsRecordInEmptyFlightTest() {
        assertFalse(flight.containsRecord(testRecord1), "Метод containsRecord должен вернуть false для пустого списка");
    }

    @Test
    void getRecordsTest() {
        flight = new Flight(testRecords);
        List<FamilyRecord> recordsCopy = flight.getRecords();
        assertEquals(2, recordsCopy.size(),
                "Метод getRecords должен вернуть копию списка с правильным размером");
        assertTrue(recordsCopy.contains(testRecord1),
                "Копия списка должна содержать первую запись");
        assertTrue(recordsCopy.contains(testRecord2),
                "Копия списка должна содержать вторую запись");
        recordsCopy.add(new FamilyRecord(3, 5)); // Проверяем, что изменение копии не влияет на оригинал
        assertEquals(2, flight.getRecords().size(),
                "Изменение копии списка не должно влиять на оригинальный список");
    }

    @Test
    void testFlightConstructorCallsGenerator() {
        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt(1, 11)).thenReturn(5);  // Всегда возвращает рейс №5
        when(mockRandom.nextInt(1, 9)).thenReturn(3);   // Всегда 3 человека в семье

        RandomFamilyGenerator.setRandom(mockRandom);

        Flight flight = new Flight(10);

        verify(mockRandom, times(10)).nextInt(1, 11); // Проверка вызова для номера рейса
        verify(mockRandom, times(10)).nextInt(1, 9);  // Проверка вызова для размера семьи

        assertEquals(10, flight.getRecords().size());
        assertTrue(flight.getRecords().stream().allMatch(r -> r.flightNumber() == 5 && r.familyAmount() == 3));
    }

}