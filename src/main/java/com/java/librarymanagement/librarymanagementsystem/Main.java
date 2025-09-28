package com.java.librarymanagement.librarymanagementsystem;

import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    private static Library library;
    private static Scanner scanner;

    public static void main(String[] args) {
        library = new Library();
        scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add New Book");
            System.out.println("2. Add New Magazine");
            System.out.println("3. Add New User");
            System.out.println("4. Borrow Item");
            System.out.println("5. Return Item");
            System.out.println("6. Display All Items");
            System.out.println("7. Display All Users");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addMagazine();
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    borrowItem();
                    break;
                case 5:
                    returnItem();
                    break;
                case 6:
                    displayAllItems();
                    break;
                case 7:
                    displayAllUsers();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for using Library Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private static void addBook() {
        System.out.println("\n=== Add New Book ===");
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Serial Number: ");
        String serialNumber = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Number of Pages: ");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Language: ");
        String language = scanner.nextLine();

        Book book = new Book(title, author, serialNumber, genre, LocalDate.now(), publisher, price, pages, isbn, language);
        library.addItem(book);
        System.out.println("Book added successfully!");
    }

    private static void addMagazine() {
        System.out.println("\n=== Add New Magazine ===");
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author/Editor: ");
        String author = scanner.nextLine();
        System.out.print("Enter Serial Number: ");
        String serialNumber = scanner.nextLine();
        System.out.print("Enter Genre/Category: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Issue Number: ");
        String issueNumber = scanner.nextLine();
        System.out.print("Enter Language: ");
        String language = scanner.nextLine();

        Magazine magazine = new Magazine(title, author, serialNumber, genre, LocalDate.now(), publisher, price, issueNumber, language);
        library.addItem(magazine);
        System.out.println("Magazine added successfully!");
    }

    private static void addUser() {
        System.out.println("\n=== Add New User ===");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        User user = new User(name, email, phone, address);
        library.addUser(user);
        System.out.println("User added successfully!");
    }

    private static void borrowItem() {
        System.out.println("\n=== Borrow Item ===");
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Item Serial Number: ");
        String serialNumber = scanner.nextLine();

        User user = library.findUser(userName);
        LibraryItems item = library.findItem(serialNumber);

        if (user != null && item != null) {
            item.borrowItem(user);
            System.out.println("Item borrowed successfully!");
        } else {
            System.out.println("Failed to borrow item. Please check user name and serial number.");
        }
    }

    private static void returnItem() {
        System.out.println("\n=== Return Item ===");
        System.out.print("Enter Serial Number: ");
        String serialNumber = scanner.nextLine();
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();

        User user = library.findUser(userName);
        if (user != null) {
            library.returnItem(serialNumber, user.getUserId());
        } else {
            System.out.println("User not found. Please check the name and try again.");
        }
    }

    private static void displayAllItems() {
        System.out.println("\n=== All Library Items ===");
        library.getItems().forEach(item -> System.out.println(item.toString()));
    }

    private static void displayAllUsers() {
        System.out.println("\n=== All Library Users ===");
        library.getUsers().forEach(user -> System.out.println(user.toString()));
    }
}
