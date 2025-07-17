import model.FamilyRecord;
import org.junit.jupiter.api.Test;
import service.FlightProcessing;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FlightProcessingTest {

    @Test
    void countPeopleByFlightEmptyListTest() {
        List<FamilyRecord> emptyList = List.of();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        FlightProcessing.countPeopleByFlight(emptyList);

        String output = out.toString().trim();
        assertEquals("", output, "При пустом списке вывод должен быть пустым");
    }

    @Test
    void countPeopleByFlightSingleFlightTest() {
        List<FamilyRecord> records = List.of(
                new FamilyRecord(1, 3),
                new FamilyRecord(1, 2)
        );
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        FlightProcessing.countPeopleByFlight(records);

        String output = out.toString().trim();
        assertEquals("flight 1: 5 passengers", output, "Ожидается корректный подсчет пассажиров для одного рейса");
    }

    @Test
    void countPeopleByFlightMultipleFlightsTest() {
        List<FamilyRecord> records = List.of(
                new FamilyRecord(1, 3),
                new FamilyRecord(2, 2),
                new FamilyRecord(1, 1),
                new FamilyRecord(3, 4)
        );
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        FlightProcessing.countPeopleByFlight(records);

        String output = out.toString();
        assertTrue(output.contains("flight 1: 4 passengers"), "Ожидается корректный подсчет для рейса 1");
        assertTrue(output.contains("flight 2: 2 passengers"), "Ожидается корректный подсчет для рейса 2");
        assertTrue(output.contains("flight 3: 4 passengers"), "Ожидается корректный подсчет для рейса 3");
    }

    @Test
    void countPeopleByFlightNoDataTest() {
        List<FamilyRecord> records = List.of(
                new FamilyRecord(1, 0)
        );
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        FlightProcessing.countPeopleByFlight(records);

        String output = out.toString().trim();
        assertEquals("flight 1: no data found", output, "Ожидается сообщение об отсутствии данных для рейса с нулевым количеством пассажиров");
    }
}