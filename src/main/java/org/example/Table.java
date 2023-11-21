package org.example;


public class Table {
    private final int id;
    private boolean free;


    public Table(int id) {
        this.id = id;
        this.free = true;
    }

    public int getId() {
        return id;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
    public String toString(){
        return String.format("Столик №%d", id);
    }
}
