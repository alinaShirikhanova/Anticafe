package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class VisitCollection {

    private static List<Visit> visits = new ArrayList<>();

    public static void createVisit(Client client, Table table){
        Visit visit = new Visit(client, table, LocalDateTime.now());
        visits.add(visit);
    }

    public static void finishVisit(int visitId){
        Optional<Visit> visit = visits.stream().
                filter(v -> v.getId() == visitId).findFirst();
        visit.ifPresent(value -> value.calculateDuration(LocalDateTime.now()));

    }

    public static double getAverageTimeOfTable(int tableId){
        return visits.stream()
                .filter(v -> v.getTable().getId() == tableId)
                .mapToLong(Visit::getDuration)
                .average().orElseThrow();
    }

    public static double getMostPopularTable(){
        Map<Integer, List<Visit>> grouped = visits.stream().collect(Collectors.groupingBy(v -> v.getTable().getId()));

    }


}
