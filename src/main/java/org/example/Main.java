package org.example;

import java.security.cert.TrustAnchor;
import java.time.*;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static String menu = "1. Занять столик\n" +
            "2. Освободить столик\n" +
            "3. Просмотреть занятые столики\n" +
            "4. Просмотреть свободные столики\n" +
            "5. Посмотреть, сколько минут сидят за каждым столиком\n" +
            "6. Посмотреть, сколько гостям нужно заплатить (конкретным)\n" +
            "7. Посмотреть, сколько придется заплатить всем гостям за столиками, если они прямо сейчас покинут антикафе";
    private static Scanner in = new Scanner(System.in);
    private static int lastId = 0;

    public static void main(String[] args) {
        System.out.println("Приветствуем в нашем Антикафе!");
        while (true) {
            System.out.println("Выберите действие: ");
            System.out.println(menu);
            int option = in.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Список свободных столиков: ");
                    for (Table table: VisitService.getFreeTables()){
                        System.out.printf("%d. %s", table.getId(), table);
                    }
            }
        }

    }
}