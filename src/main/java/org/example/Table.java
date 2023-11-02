package org.example;

import java.util.ArrayList;

public class Table {
    private final int id;
    private boolean free;
    private Client client;
    ArrayList<Client> clients = new ArrayList<>();

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

    public Client getClient() {
        return client;
    }

    public void takeTable(Client client) {
        this.client = client;
        setFree(false);
        clients.add(client);
    }

    public void freeTable() {
        this.client = null;
        setFree(true);
    }
}
