package com.java.librarymanagement.librarymanagementsystem;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class LibraryItems implements Serializable {
    private String title;
    private String author;
    private String serialNumber;
    private String genre;
    private LocalDate publishDate;
    private String publisher;
    private double price;
    protected boolean isBorrowed;
    protected LocalDate borrowDate;
    protected LocalDate dueDate;
    protected String borrowedBy;

    public LibraryItems(String title, String author, String serialNumber,
                        String genre, LocalDate publishDate, String publisher, double price) {
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.price = price;
        this.isBorrowed = false;
    }

    public abstract void borrowItem(User user);
    public abstract int getBorrowDurationDays();

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public LocalDate getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDate publishDate) { this.publishDate = publishDate; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isBorrowed() { return isBorrowed; }
    public void setBorrowed(boolean borrowed) { isBorrowed = borrowed; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getBorrowedBy() { return borrowedBy; }
    public void setBorrowedBy(String borrowedBy) { this.borrowedBy = borrowedBy; }

    @Override
    public String toString() {
        return String.format("Title: %s | Author: %s | Serial: %s | Genre: %s | Price: $%.2f | Status: %s",
                title, author, serialNumber, genre, price, isBorrowed ? "Borrowed" : "Available");
    }
}

