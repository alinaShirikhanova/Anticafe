package org.example;

import java.time.Duration;
import java.time.LocalDateTime;

public class Client {
    private final int id;
    private static int lastId = 0;
    private String name;





    public Client(String name) {
        this.id = lastId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
