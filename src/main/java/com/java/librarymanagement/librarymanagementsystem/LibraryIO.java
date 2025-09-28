package com.java.librarymanagement.librarymanagementsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryIO {

    // Save library items to file
    public static void saveItemToFile(List<LibraryItems> libraryItems, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(libraryItems);
            System.out.println("Library Items saved to " + fileName + " successfully.");
        } catch (IOException e) {
            System.out.println("Error saving items: " + e.getMessage());
        }
    }

    // Load library items from file
    public static List<LibraryItems> loadItemsFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) { // FIXED: Changed from file.exists() to !file.exists()
            System.out.println("No previous items file found. Starting with empty list.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            @SuppressWarnings("unchecked")
            List<LibraryItems> items = (List<LibraryItems>) ois.readObject();
            System.out.println("Library Items loaded from " + fileName + " successfully.");
            return items;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading items: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Save users to file
    public static void saveUserToFile(List<User> users, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(users);
            System.out.println("Users saved to " + fileName + " successfully.");
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // Load users from file
    public static List<User> loadUsersFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) { // FIXED: Changed from file.exists() to !file.exists()
            System.out.println("No previous users file found. Starting with empty list.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            @SuppressWarnings("unchecked")
            List<User> users = (List<User>) ois.readObject();
            System.out.println("Users loaded from " + fileName + " successfully.");
            return users;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Save borrowed items map to file
    public static void saveBorrowedItems(Map<String, String> borrowedItems, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(borrowedItems);
            System.out.println("Borrowed Items saved to " + fileName + " successfully.");
        } catch (IOException e) {
            System.out.println("Error saving borrowed items: " + e.getMessage());
        }
    }

    // Load borrowed items map from file
    public static Map<String, String> loadBorrowedItems(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) { // FIXED: This one was already correct
            System.out.println("No previous borrowed items file found. Starting with empty map.");
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            @SuppressWarnings("unchecked")
            Map<String, String> borrowedItems = (Map<String, String>) ois.readObject();
            System.out.println("Borrowed Items loaded from " + fileName + " successfully.");
            return borrowedItems;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading borrowed items: " + e.getMessage());
            return new HashMap<>();
        }
    }
}