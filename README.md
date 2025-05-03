
# 📚 Library Management System

Welcome to the **Library Management System** project! 🚀 This system, built using **Java** and **Object-Oriented Programming (OOP)** principles, helps manage books, library members, borrowing/returning books, and keeps track of transactions and due dates.

---

## 🛠 Features

✨ **Key Features** of the Library Management System:

- ➕ **Add Books**: Easily add new books to the library collection.
- 🔍 **Search Books**: Search for books by title, author, or ISBN.
- 🧾 **Borrow & Return Books**: Keep track of books borrowed by members and the return process.
- ❌ **Remove Books**: Delete outdated or unwanted books from the system.
- 👤 **Manage Members**: Handle library members (students, staff, etc.), with the ability to register, borrow, and return books.
- 📅 **Transaction Tracking**: Track borrowing and returning transactions with dates and due dates.
- 🔒 **Error Handling**: Proper validation and handling of common errors (book not found, member not found, etc.).

---

## 🚀 Technologies Used

This project uses the following technologies:

- **Java** (Core language for implementation)
- **OOP Concepts**:
  - **Encapsulation**: Protects internal state with getters and setters.
  - **Inheritance**: Enables code reuse with `LibraryItem` as a base class for `Book`.
  - **Abstraction**: Hides implementation details using abstract classes like `LibraryItem`.
  - **Polymorphism**: Allows overriding methods in subclasses for specific behavior.
  - **Composition**: Services interact with repositories to manage data.
- **Collections**: Used to store books, members, and transactions in memory (HashMaps, ArrayLists).

---

## 📁 Project Structure

Here’s a quick overview of the project structure:

```plaintext
📁 library-management-system/
├── 📁 src/                       # Java source files
│ ├── 📁 com/library/             # Main package
│ │ ├── Main.java                # Entry point to the system
│ │ ├── 📁 model/                # Contains data models (Book, Member, etc.)
│ │ ├── 📁 repository/           # Data access and CRUD operations
│ │ ├── 📁 service/              # Core business logic and operations
│ │ ├── 📁 exception/            # Custom exception handling
│ │ └── 📁 util/                 # Utility classes (DateUtil, IdGenerator)
├── 📄 README.md                 # Documentation
└── 📄 .gitignore                # Ignore unwanted files in git
````

### Key Classes:

1. **Model Classes**:

   * `LibraryItem`: Abstract class for all library items
   * `Book`: Represents a book with properties like title, author, ISBN
   * `Member`: Represents a library member who can borrow books
   * `Transaction`: Handles record keeping for borrow/return activities

2. **Repository Classes**:

   * Manages data storage (CRUD operations) using collections like `HashMap`
   * `BookRepository`: Stores book data
   * `MemberRepository`: Stores member data
   * `TransactionRepository`: Stores transaction data

3. **Service Classes**:

   * `BookService`: Handles business logic for books (adding, searching, etc.)
   * `MemberService`: Handles member-related logic (registration, borrowing, etc.)
   * `TransactionService`: Manages borrow and return transactions

4. **Exception Classes**:

   * `BookNotFoundException`: Thrown when a book is not found
   * `MemberNotFoundException`: Thrown when a member is not found
   * `TransactionException`: Handles errors in transaction processing

5. **Utility Classes**:

   * `IdGenerator`: Generates unique IDs for books, members, and transactions
   * `DateUtil`: Helps with date operations like calculating due dates

---

## 🛠️ Getting Started

1. **Clone the repository**:

   ```bash
   git clone https://github.com/maheen-736/library-management-system.git
   cd library-management-system
   ```

2. **Compile and run the program**:

   ```bash
   javac src/*.java
   java src.Main
   ```

3. The system will prompt you with a console-based menu to interact with. Follow the instructions to add books, manage members, and track transactions.

---

## 🌱 Future Improvements

Here are some **future enhancements** to make this project even better:

* 💻 **GUI Integration**: Create a user-friendly interface using JavaFX or Swing.
* 🗃️ **Database Integration**: Implement database support (e.g., MySQL, SQLite) for persistent data storage.
* 🔐 **User Authentication**: Add login functionality for library staff to manage the system securely.
* 💸 **Fines System**: Implement fines for overdue books and late returns.
* 📊 **Reports and Analytics**: Generate reports on popular books, late returns, and other useful statistics.

---

## 📬 Contact

Feel free to reach out with any questions or suggestions:

* **Maheen**
  📧 \[[maheen.khadim736@gmail.com](mailto:maheen.khadim736@gmail.com)]

---

## 📝 License

This project is open-source and licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute!

```
