package org.example;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class VisitService {
    private static double pricePerMinute;


    public double getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }

    public static List<Visit> getVisits() {
        return visits;
    }

    public static void setVisits(List<Visit> visits) {
        VisitService.visits = visits;
    }

    private static List<Visit> visits = new ArrayList<>();

    public static void createVisit(Client client, Table table) {
        Visit visit = new Visit(client, table, LocalDateTime.now());
        table.setFree(false);
        visits.add(visit);
    }

    public static long getCurrentDurationByTableId(int tableId) {
        Visit visit = visits.stream().
                filter(v -> v.getTable().getId() == tableId).findFirst().orElseThrow();
        return visit.calculateDuration();
    }

    public static Map<Table, Long> getTotalCurrentDuration(){
        Map<Table, Long> currentDurations = new HashMap<>();
        for (Table table: TableService.getReservedTables()) {
            currentDurations.put(table, getCurrentDurationByTableId(table.getId()));
        }
        return currentDurations;
    }


    public static double getCurrentCostByClientId(int clientId){
        Visit visit = visits.stream().
                filter(v -> v.getClient().getId() == clientId).findFirst().orElseThrow();
        return visit.calculateCurrentCost(pricePerMinute);
    }

    public static double getTotalCurrentCost(){
        double totalCost = 0;
        for (Table table: TableService.getReservedTables()) {
           Visit visit = visits.stream()
                   .filter(v -> v.getTable() == table)
                   .findFirst().orElseThrow();
           totalCost += visit.calculateCurrentCost(pricePerMinute);

        }
        return totalCost;
    }

    public static List<Table> getFreeTables(){
        return TableService.getFreeTables();
    }
    public static List<Table> getReservedTables(){
        return TableService.getReservedTables();
    }
//
//    public static double getAverageTimeOfTable(int tableId) {
//        return visits.stream()
//                .filter(v -> v.getTable().getId() == tableId)
//                .mapToLong(Visit::getDuration)
//                .average().orElseThrow();
//    }
//
//    public static double getMostPopularTable() {
//        Map<Integer, List<Visit>> grouped = visits.stream().collect(Collectors.groupingBy(v -> v.getTable().getId()));
//
//
//    }


}
