package model;

import utils.RandomFamilyGenerator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.ArrayList;

public class Flight {
    private static final Logger logger = LogManager.getLogger(Flight.class);
    private final List<FamilyRecord> records;

    public Flight(int recNum) {
        logger.info("Создание объекта Flight с " + recNum + " случайными записями");
        records = new ArrayList<>();

        for (int i = 0; i < recNum; ++i) {
            records.add(RandomFamilyGenerator.generateRandomFamily());
        }
        logger.info("Создано " + records.size() + " случайных записей о семьях");
    }

    public Flight(ArrayList<FamilyRecord> records) {
        logger.info("Создание объекта Flight с " + records.size() + " переданными записями");
        this.records = records;
    }

    public void addRecord(FamilyRecord record) {
        logger.debug("Добавление записи: рейс " + record.flightNumber() + ", семья из " + record.familyAmount() + " человек");
        records.add(record);
    }

    public boolean removeRecord(FamilyRecord record) {
        logger.debug("Удаление записи: рейс " + record.flightNumber() + ", семья из " + record.familyAmount() + " человек");
        return records.remove(record);
    }

    public void removeRecordByIndex(int index) {
        logger.debug("Удаление записи по индексу: " + index);
        records.remove((index));
    }

    public boolean containsRecord (FamilyRecord record) {
        boolean contains = records.contains((record));
        logger.debug("Проверка наличия записи: " + (contains ? "найдена" : "не найдена"));
        return contains;
    }

    public List<FamilyRecord> getRecords() {
        logger.debug("Получение копии списка записей. Размер: " + records.size());
        return new ArrayList<>(records);
    }

}