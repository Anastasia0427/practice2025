package org.example;

import java.util.List;

public class FlightProcessing {

    public static void countPeopleByFlight(List<FamilyRecord> records) {
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
                        System.out.println("flight " + flightNumber + ": no data found");
                    } else {
                        System.out.println("flight " + flightNumber + ": " + count + " passengers");
                    }
                });
    }

}