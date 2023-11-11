package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TableService {


    private static Map<Integer, Table> tables = new HashMap<>({{
            (1, new Table(1),
        2, new Table(2),
        3, new Table(3),
        4, new Table(4)}};

    public TableService() {
        for (int i = 1; i < 10; i++) {
            tables.put(i, new Table(i));
        }
    }



    public Map<Integer, Table> getTables() {
        return tables;
    }


    public static List<Table> getFreeTables(){
        return tables.values().stream()
                .filter(Table::isFree)
                .toList();
    }

    public static List<Table> getReservedTables(){
        return tables.values().stream()
                .filter(t -> !t.isFree())
                .toList();
    }

//    public static double getCostsOfAllTables(){
//        return tables.values().stream()
//                .filter(t -> !t.isFree())
//                .mapToDouble(t -> t.getClient().getCurrentTime() * pricePerMinute)
//                .sum();
//    }
//
//
//    public static double getCostOfTable(int tableId){
//        Optional<Table> table = tables.values().stream()
//                .filter(t -> t.getId() == tableId)
//                .findFirst();
//        if (table.isPresent() && !table.get().isFree())
//            return table.get().getClient().getCurrentTime() * pricePerMinute;
//        throw new RuntimeException();
//    }

}
