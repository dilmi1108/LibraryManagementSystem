package com.java.librarymanagement.librarymanagementsystem;

import java.time.LocalDate;

public class Book extends LibraryItems {
    private int pageCount;
    private String isbn;
    private String language;

    public Book(String title, String author, String serialNumber, String genre,
                LocalDate publishDate, String publisher, double price,
                int pageCount, String isbn, String language) {
        super(title, author, serialNumber, genre, publishDate, publisher, price);
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.language = language;
    }

    @Override
    public void borrowItem(User user) {
        if (!isBorrowed) {
            isBorrowed = true;
            borrowDate = LocalDate.now();
            dueDate = borrowDate.plusDays(getBorrowDurationDays());
            borrowedBy = user.getName();
            user.addBorrowedItem(this);
            System.out.println(user.getName() + " borrowed the book: " + getTitle());
            System.out.println("Due date: " + dueDate);
        } else {
            System.out.println("The book " + getTitle() + " is already borrowed");
        }
    }

    @Override
    public int getBorrowDurationDays() {
        return 14; // Books can be borrowed for 14 days
    }

    // Getters and Setters
    public int getPageCount() { return pageCount; }
    public void setPageCount(int pageCount) { this.pageCount = pageCount; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
