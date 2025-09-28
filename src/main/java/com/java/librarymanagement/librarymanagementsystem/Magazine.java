package com.java.librarymanagement.librarymanagementsystem;

import java.time.LocalDate;

public class Magazine extends LibraryItems {
    private String issueNumber;
    private String frequency; // Monthly, Weekly, etc.

    public Magazine(String title, String author, String serialNumber, String genre,
                    LocalDate publishDate, String publisher, double price,
                    String issueNumber, String frequency) {
        super(title, author, serialNumber, genre, publishDate, publisher, price);
        this.issueNumber = issueNumber;
        this.frequency = frequency;
    }

    @Override
    public void borrowItem(User user) {
        if (!isBorrowed) {
            isBorrowed = true;
            borrowDate = LocalDate.now();
            dueDate = borrowDate.plusDays(getBorrowDurationDays());
            borrowedBy = user.getName();
            user.addBorrowedItem(this);
            System.out.println(user.getName() + " borrowed the magazine: " + getTitle());
            System.out.println("Due date: " + dueDate);
        } else {
            System.out.println("The magazine " + getTitle() + " is already borrowed");
        }
    }

    @Override
    public int getBorrowDurationDays() {
        return 7; // Magazines can be borrowed for 7 days
    }

    // Getters and Setters
    public String getIssueNumber() { return issueNumber; }
    public void setIssueNumber(String issueNumber) { this.issueNumber = issueNumber; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
}

