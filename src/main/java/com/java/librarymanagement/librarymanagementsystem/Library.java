package com.java.librarymanagement.librarymanagementsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private List<LibraryItems> libraryItems;
    private List<User> userList;
    private Map<String, String> borrowedItems;
    private static final double FINE_PER_DAY = 0.50;

    public Library() {
        libraryItems = new ArrayList<>();
        userList = new ArrayList<>();
        borrowedItems = new HashMap<>();
    }

    public void addItem(LibraryItems libraryItem) {
        if (findItemBySerial(libraryItem.getSerialNumber()) == null) {
            libraryItems.add(libraryItem);
            System.out.println("Item added successfully: " + libraryItem.getTitle());
        } else {
            System.out.println("Item with serial number " + libraryItem.getSerialNumber() + " already exists");
        }
    }

    public void addUser(User user) {
        if (findUserById(user.getUserId()) == null) {
            userList.add(user);
            System.out.println("User added successfully: " + user.getName());
        } else {
            System.out.println("User with ID " + user.getUserId() + " already exists");
        }
    }

    public LibraryItems findItemBySerial(String serialNumber) {
        return libraryItems.stream()
                .filter(item -> item.getSerialNumber().equals(serialNumber))
                .findFirst()
                .orElse(null);
    }

    public User findUserById(String userId) {
        return userList.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public User findUser(String name) {
        return userList.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public LibraryItems findItem(String serialNumber) {
        return findItemBySerial(serialNumber);
    }

    public List<LibraryItems> getItems() {
        return new ArrayList<>(libraryItems);
    }

    public List<User> getUsers() {
        return new ArrayList<>(userList);
    }

    public void borrowItem(String serialNumber, String userId) {
        LibraryItems item = findItemBySerial(serialNumber);
        User user = findUserById(userId);

        if (item == null) {
            System.out.println("Item with serial number " + serialNumber + " not found");
            return;
        }

        if (user == null) {
            System.out.println("User with ID " + userId + " not found");
            return;
        }

        if (!user.canBorrow()) {
            System.out.println("User cannot borrow: " +
                    (user.getFineAmount() > 0 ? "Outstanding fine: $" + user.getFineAmount() :
                            "Maximum borrow limit reached"));
            return;
        }

        if (item.isBorrowed()) {
            System.out.println("Item " + item.getTitle() + " is already borrowed");
            return;
        }

        item.borrowItem(user);
        borrowedItems.put(item.getSerialNumber(), user.getUserId());
    }

    public void returnItem(String serialNumber, String userId) {
        LibraryItems item = findItemBySerial(serialNumber);
        User user = findUserById(userId);

        if (item == null || user == null) {
            System.out.println("Item or user not found");
            return;
        }

        if (!item.isBorrowed() || !item.getBorrowedBy().equals(user.getName())) {
            System.out.println("This item is not borrowed by the specified user");
            return;
        }

        // Calculate fine for overdue items
        LocalDate today = LocalDate.now();
        if (today.isAfter(item.getDueDate())) {
            long daysOverdue = ChronoUnit.DAYS.between(item.getDueDate(), today);
            double fine = daysOverdue * FINE_PER_DAY;
            user.addFine(fine);
            System.out.println("Item is " + daysOverdue + " days overdue. Fine applied: $" + fine);
        }

        // Return the item
        item.setBorrowed(false);
        item.setBorrowDate(null);
        item.setDueDate(null);
        item.setBorrowedBy(null);
        user.removeBorrowedItem(item);
        borrowedItems.remove(serialNumber);

        System.out.println("Item " + item.getTitle() + " returned successfully by " + user.getName());
    }

    public void renewItem(String serialNumber, String userId) {
        LibraryItems item = findItemBySerial(serialNumber);
        User user = findUserById(userId);

        if (item == null || user == null) {
            System.out.println("Item or user not found");
            return;
        }

        if (!item.isBorrowed() || !item.getBorrowedBy().equals(user.getName())) {
            System.out.println("This item is not borrowed by the specified user");
            return;
        }

        if (LocalDate.now().isAfter(item.getDueDate())) {
            System.out.println("Cannot renew overdue item. Please return and pay fine first.");
            return;
        }

        item.setDueDate(LocalDate.now().plusDays(item.getBorrowDurationDays()));
        System.out.println("Item renewed successfully. New due date: " + item.getDueDate());
    }

    public List<LibraryItems> searchItems(String query) {
        String lowerQuery = query.toLowerCase();
        return libraryItems.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(lowerQuery) ||
                        item.getAuthor().toLowerCase().contains(lowerQuery) ||
                        item.getGenre().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public List<LibraryItems> getAvailableItems() {
        return libraryItems.stream()
                .filter(item -> !item.isBorrowed())
                .collect(Collectors.toList());
    }

    public List<LibraryItems> getOverdueItems() {
        LocalDate today = LocalDate.now();
        return libraryItems.stream()
                .filter(item -> item.isBorrowed() && item.getDueDate().isBefore(today))
                .collect(Collectors.toList());
    }

    public void generateOverdueReport() {
        List<LibraryItems> overdueItems = getOverdueItems();
        System.out.println("\n=== OVERDUE ITEMS REPORT ===");
        if (overdueItems.isEmpty()) {
            System.out.println("No overdue items found.");
        } else {
            overdueItems.forEach(item -> {
                long daysOverdue = ChronoUnit.DAYS.between(item.getDueDate(), LocalDate.now());
                System.out.printf("%s | Borrowed by: %s | Days overdue: %d%n",
                        item.getTitle(), item.getBorrowedBy(), daysOverdue);
            });
        }
    }

    public void generateLibraryStatistics() {
        System.out.println("\n=== LIBRARY STATISTICS ===");
        System.out.println("Total Items: " + libraryItems.size());
        System.out.println("Total Users: " + userList.size());
        System.out.println("Items Borrowed: " + borrowedItems.size());
        System.out.println("Items Available: " + getAvailableItems().size());
        System.out.println("Overdue Items: " + getOverdueItems().size());

        Map<String, Long> itemsByGenre = libraryItems.stream()
                .collect(Collectors.groupingBy(LibraryItems::getGenre, Collectors.counting()));
        System.out.println("\nItems by Genre:");
        itemsByGenre.forEach((genre, count) -> System.out.println(genre + ": " + count));
    }

    // Existing getters
    public List<LibraryItems> getLibraryItems() { return libraryItems; }
    public List<User> getUserList() { return userList; }
    public Map<String, String> getBorrowedItems() { return borrowedItems; }

    // Add toString methods for proper display
    @Override
    public String toString() {
        return "Library{" +
                "items=" + libraryItems.size() +
                ", users=" + userList.size() +
                '}';
    }
}
