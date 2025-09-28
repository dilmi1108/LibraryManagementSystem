package com.java.librarymanagement.librarymanagementsystem;

import java.time.LocalDate;

public class DVD extends LibraryItems {
    private int durationMinutes;
    private String director;
    private String rating; // PG, PG-13, R, etc.

    public DVD(String title, String director, String serialNumber, String genre,
               LocalDate publishDate, String publisher, double price,
               int durationMinutes, String rating) {
        super(title, director, serialNumber, genre, publishDate, publisher, price);
        this.durationMinutes = durationMinutes;
        this.director = director;
        this.rating = rating;
    }

    @Override
    public void borrowItem(User user) {
        if (!isBorrowed) {
            isBorrowed = true;
            borrowDate = LocalDate.now();
            dueDate = borrowDate.plusDays(getBorrowDurationDays());
            borrowedBy = user.getName();
            user.addBorrowedItem(this);
            System.out.println(user.getName() + " borrowed the DVD: " + getTitle());
            System.out.println("Due date: " + dueDate);
        } else {
            System.out.println("The DVD " + getTitle() + " is already borrowed");
        }
    }

    @Override
    public int getBorrowDurationDays() {
        return 3; // DVDs can be borrowed for 3 days
    }

    // Getters and Setters
    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
}
