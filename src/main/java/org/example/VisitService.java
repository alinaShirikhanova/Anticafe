package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class VisitService {
    private static double pricePerMinute = 5;

    public static String getFormatStartTime(Visit visit){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        return dateTimeFormatter.format(visit.getStartTime());
    }

    public static Visit getVisitById(int visitId){
        return visits.stream()
                .filter(v -> v.getId() == visitId)
                .findFirst().orElseThrow();
    }

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

    public static Visit createVisit(Client client, int tableId) {
        Table table = TableService.tables.get(tableId);
        Visit visit = new Visit(client, table, LocalDateTime.now());
        table.setFree(false);
        visits.add(visit);
        return visit;
    }

    public static Visit finishVisit(int tableId) {
        Table table = TableService.tables.get(tableId);
        Visit visit = visits.stream()
                        .filter(v -> v.getTable().getId() == tableId && !v.isFinished()).findFirst().orElseThrow();
        visit.setDuration(getCurrentDurationByTableId(tableId));
        visit.setCost(getCurrentCostByTableId(tableId));

        visit.setFinished(true);
        table.setFree(true);
        return visit;
    }

    public static long getCurrentDurationByTableId(int tableId) {
        Visit visit = visits.stream().
                filter(v -> v.getTable().getId() == tableId && !v.isFinished()).findFirst().orElseThrow();
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

    public static double getCurrentCostByTableId(int tableId){
        Visit visit = visits.stream().
                filter(v -> v.getTable().getId() == tableId && !v.isFinished()).findFirst().orElseThrow();
        return visit.calculateCurrentCost(pricePerMinute);
    }

    public static double getTotalCurrentCost(){
        double totalCost = 0;
        for (Table table: TableService.getReservedTables()) {
           Visit visit = visits.stream()
                   .filter(v -> v.getTable() == table && !v.isFinished()).findFirst().orElseThrow();
           totalCost += visit.calculateCurrentCost(pricePerMinute);

        }
        return totalCost;
    }

    public static double getTotalCostOfAllTime(){

        return visits.stream()
                .filter(Visit::isFinished).mapToDouble(Visit::getCost).sum();
    }

    public static Map.Entry<Table, Long> getTheMostPopularTable(){

        Map<Table, Long> map = visits.stream()
                .filter(Visit::isFinished).collect(Collectors.groupingBy(Visit::getTable, Collectors.counting()));
        return map.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElseThrow();
    }

    public static Map.Entry<Table, DoubleSummaryStatistics> getTheMostEarnedTable(){

        Map<Table, DoubleSummaryStatistics> map = visits.stream()
                .filter(Visit::isFinished).collect(Collectors.groupingBy(Visit::getTable, Collectors.summarizingDouble(Visit::getCost)));
        return map.entrySet().stream().max(Comparator.comparing(entry -> entry.getValue().getSum())).orElseThrow();
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
