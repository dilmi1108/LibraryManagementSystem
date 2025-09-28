# Library Management System

A Java-based console application for managing a library's books, magazines, users, and borrowing operations.

## Features

- **Item Management**
  - Add new books with details (title, author, ISBN, etc.)
  - Add new magazines with details (title, editor, issue number, etc.)
  - Display all library items
  - Track item availability

- **User Management**
  - Add new users with contact information
  - Display all registered users
  - Track user borrowing history

- **Borrowing Operations**
  - Borrow items
  - Return items
  - Track due dates
  - Calculate fines for overdue items

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, etc.) or command line tools

### Project Structure

```
LibraryMS/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── java/
│                   └── librarymanagement/
│                       └── librarymanagementsystem/
│                           ├── Book.java
│                           ├── DVD.java
│                           ├── Library.java
│                           ├── LibraryIO.java
│                           ├── LibraryItems.java
│                           ├── Magazine.java
│                           ├── Main.java
│                           └── User.java
└── pom.xml
```

### Installation

1. Clone the repository or download the source code
2. Open the project in your preferred Java IDE
3. Build the project using Maven:
   ```
   mvn clean install
   ```

## Usage

Run the `Main` class to start the application. You will be presented with a menu:

```
=== Library Management System ===
1. Add New Book
2. Add New Magazine
3. Add New User
4. Borrow Item
5. Return Item
6. Display All Items
7. Display All Users
8. Exit
```

### Adding a Book

1. Select option 1
2. Enter the requested details:
   - Title
   - Author
   - Serial Number
   - Genre
   - Publisher
   - Price
   - Number of Pages
   - ISBN
   - Language

### Adding a Magazine

1. Select option 2
2. Enter the requested details:
   - Title
   - Author/Editor
   - Serial Number
   - Genre/Category
   - Publisher
   - Price
   - Issue Number
   - Language

### Adding a User

1. Select option 3
2. Enter the requested details:
   - Name
   - Email
   - Phone
   - Address

### Borrowing an Item

1. Select option 4
2. Enter:
   - User Name
   - Item Serial Number

### Returning an Item

1. Select option 5
2. Enter:
   - Serial Number
   - User Name

## Features Implementation

### Item Management
- Books and Magazines inherit from the abstract `LibraryItems` class
- Each item has a unique serial number
- Items track their borrowed status and due dates

### User Management
- Users are identified by their name and unique ID
- System tracks borrowed items per user
- Implements fine calculation for overdue items

### Borrowing System
- Validates user and item availability
- Sets due dates automatically
- Tracks overdue items
- Calculates fines for late returns

## Contributing

Feel free to fork the project and submit pull requests for any improvements.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

Dilmi Sooriyaarachchi
## Acknowledgments

- Thanks to all contributors
- Inspired by real-world library management systems
