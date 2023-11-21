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
    private boolean isFinished = false;

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Visit.lastId = lastId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

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

//    public long getDuration() {
//        return duration;
//    }

    public long calculateDuration() {
        return Duration.between( startTime, LocalDateTime.now()).toSeconds();
    }

    public double calculateCurrentCost(double pricePerMinute){
        return calculateDuration() * pricePerMinute;
    }

    public void finishVisit(){
        getTable().setFree(true);
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

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", client=" + client +
                ", table=" + table +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", cost=" + cost +
                ", isFinished=" + isFinished +
                '}';
    }
}
