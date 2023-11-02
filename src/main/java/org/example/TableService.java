package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TableService {
    private double pricePerMinute;

    private Map<Integer, Table> tables = new HashMap<>();

    public TableService(double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
        for (int i = 1; i < 10; i++) {
            tables.put(i, new Table(i));
        }
    }

    public double getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }

    public Map<Integer, Table> getTables() {
        return tables;
    }


    public void takePlace(int tableId, Client client){
        tables.get(tableId).takeTable(client);
    }


    public void takePlace(int tableId){
        tables.get(tableId).freeTable();
    }

    public List<Table> getFreeTables(){
        return tables.values().stream()
                .filter(Table::isFree)
                .toList();
    }

    public List<Table> getReservedTables(){
        return tables.values().stream()
                .filter(t -> !t.isFree())
                .toList();
    }

    public double getCostsOfAllTables(){
        return tables.values().stream()
                .filter(t -> !t.isFree())
                .mapToDouble(t -> t.getClient().getCurrentTime() * pricePerMinute)
                .sum();
    }


    public double getCostOfTable(int tableId){
        Optional<Table> table = tables.values().stream()
                .filter(t -> t.getId() == tableId)
                .findFirst();
        if (table.isPresent() && !table.get().isFree())
            return table.get().getClient().getCurrentTime() * pricePerMinute;
        throw new RuntimeException();
    }

}
