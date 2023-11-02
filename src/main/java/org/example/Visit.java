package org.example;

import java.time.Duration;
import java.time.LocalDateTime;

public class Visit {
    private int id;
    private static int lastId = 0;
    private Client client;
    private Table table;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long duration;

    private double cost;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Visit(Client client, Table table, LocalDateTime startTime) {
        this.id = lastId++;
        this.startTime = startTime;
        this.client = client;
        this.table = table;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public long getDuration() {
        return duration;
    }

    public void calculateDuration(LocalDateTime endTime) {
        setEndTime(endTime);
        this.duration = Duration.between(this.endTime, startTime).toMinutes();
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
