package com.ggjiuw;

import java.io.IOException;
import java.util.Scanner;
import static com.ggjiuw.PersonStorage.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Commands: add , remove , list , find");

        while (true) {
            System.out.print(">> ");
            String userChoice = scanner.next();

            if (userChoice.equals("add")) {
                System.out.print("Enter new Person name: ");
                String name = scanner.next();
                // знаю що мабудь потрібно додати перевірку, ци name == null(або empty), але я десь 1-2 робив PersonStorage, та я звосім не розумію як це зробити

                System.out.print("Enter new Person age: ");
                int age = scanner.nextInt();

                if (!(add(new Person(name, age)))) {
                    System.out.println("Person with name '" + name + "' already exists");
                }
            } else if (userChoice.equals("remove")) {
                System.out.print("Enter Person name to remove: ");
                String name = scanner.next();

                if (!(remove(name))) {
                    System.out.println("Something went wrong...");
                }
            } else if (userChoice.equals("list")) {
                System.out.println(getJson());
            } else if (userChoice.equals("find")) {
                System.out.print("Enter Person name to find: ");
                String name = scanner.next();

                String[] data = find(name);
                if (data != null) {
                    System.out.println("Person {\n\tname: '" + data[1] + "'\n\tage: " + data[2] + "\n}");
                } else {
                    System.out.println("Cannot find Person with name '" + name + "'");
                }
            } else if (userChoice.equals("quit")) {
                System.out.println("byeeee");
                break;
            } else {
                System.out.println("UNKNOWN COMMAND: '" + userChoice + "'");
            }
        }
    }
}