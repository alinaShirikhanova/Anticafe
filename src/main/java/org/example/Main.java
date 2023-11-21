package org.example;

import java.security.cert.TrustAnchor;
import java.sql.SQLOutput;
import java.time.*;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static String menu = """


            1. Занять столик
            2. Освободить столик
            3. Просмотреть занятые столики
            4. Просмотреть свободные столики
            5. Посмотреть, сколько минут сидят за каждым столиком
            6. Посмотреть, сколько гостям нужно заплатить (конкретным)
            7. Посмотреть, сколько придется заплатить всем гостям за столиками, если они прямо сейчас покинут антикафе
            8. Завершить визит
            9. Узнать заработок за все время
            10. Узнать заработок за все время
            11. Узнать заработок за все время
            12. Узнать, какой столик чаще всего выбирается
            13. Узнать, какой столик больше всего принес в кассу
            14. Получить список всех визитов
            15. Получить список всех завершенных визитов
            """;
    private static Scanner in = new Scanner(System.in);
    private static int lastId = 0;

    public static void main(String[] args) {
        System.out.println("\nПриветствуем в нашем Антикафе!");
        while (true) {
            System.out.println("\nВыберите действие: ");
            System.out.println(menu);
            int option = in.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("Список свободных столиков: ");
                    for (Table table : VisitService.getFreeTables()) {
                        System.out.println(table);
                    }
                    System.out.println("Введите номер столика, который хотите занять: ");
                    int tableId = in.nextInt();
                    String name = in.nextLine();
                    Visit visit = VisitService.createVisit(new Client(name), tableId);
                    System.out.println("Столик занят");
                    System.out.printf("Id вашего визита: %d\n" +
                            "Начало визита: %s", visit.getId(), VisitService.getFormatStartTime(visit));
                }
                case 2 -> {
//                    System.out.println("Введите id визита: ");
//                    int visitId = in.nextInt();
//                    Visit visit = VisitService.getVisitById(visitId);
//                    VisitService.

                }
                case 5 -> {
                    System.out.println(VisitService.getTotalCurrentDuration());
                }
                case 6 -> {
                    System.out.println("Введите номер столика, по которому хотите узнать стоимость: ");
                    int tableId = in.nextInt();
                    System.out.println(VisitService.getTotalCurrentDuration());
                    System.out.println(VisitService.getCurrentCostByTableId(tableId));
                }
                case 7 -> {
                    System.out.println(VisitService.getTotalCurrentDuration());
                    System.out.println(VisitService.getTotalCurrentCost());
                }
                case 8 -> {
                    System.out.println("Введите номер столика, по которому хотите завершить визит: ");
                    int tableId = in.nextInt();
                    VisitService.finishVisit(tableId);
                    System.out.println(VisitService.getVisits());

                }
                case 11 -> System.out.println(VisitService.getTotalCostOfAllTime());
            }
        }

    }
}
