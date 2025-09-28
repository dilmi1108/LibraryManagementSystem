package com.java.librarymanagement.librarymanagementsystem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String name;
    private String userId;
    private String email;
    private String phoneNumber;
    private LocalDate registrationDate;
    private List<LibraryItems> borrowedItems;
    private double fineAmount;
    private int maxBorrowLimit = 5;

    public User(String name, String userId, String email, String phoneNumber) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = LocalDate.now();
        this.borrowedItems = new ArrayList<>();
        this.fineAmount = 0.0;
    }

    public void addBorrowedItem(LibraryItems item) {
        if (borrowedItems.size() < maxBorrowLimit) {
            borrowedItems.add(item);
        } else {
            System.out.println("User has reached maximum borrow limit of " + maxBorrowLimit + " items");
        }
    }

    public void removeBorrowedItem(LibraryItems item) {
        borrowedItems.remove(item);
    }

    public boolean canBorrow() {
        return borrowedItems.size() < maxBorrowLimit && fineAmount == 0.0;
    }

    public void addFine(double amount) {
        this.fineAmount += amount;
    }

    public void payFine(double amount) {
        this.fineAmount = Math.max(0, this.fineAmount - amount);
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public LocalDate getRegistrationDate() { return registrationDate; }

    public List<LibraryItems> getBorrowedItems() { return borrowedItems; }

    public double getFineAmount() { return fineAmount; }

    public int getMaxBorrowLimit() { return maxBorrowLimit; }
    public void setMaxBorrowLimit(int maxBorrowLimit) { this.maxBorrowLimit = maxBorrowLimit; }

    @Override
    public String toString() {
        return String.format("User: %s (ID: %s) | Email: %s | Phone: %s | Borrowed Items: %d | Fine: $%.2f",
                name, userId, email, phoneNumber, borrowedItems.size(), fineAmount);
    }
}

