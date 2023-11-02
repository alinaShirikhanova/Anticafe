package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VisitCollection {

    private static List<Visit> visits = new ArrayList<>();

    public static void createVisit(Client client, Table table){
        Visit visit = new Visit(client, table, LocalDateTime.now());
        visits.add(visit);
    }

    public static void finishVisit(int visitId){
        Optional<Visit> visit = visits.stream().
                filter(v -> v.getId() == visitId).findFirst();
        if (visit.isPresent()){
            visit.get().calculateDuration(LocalDateTime.now());
        }

    }


}
