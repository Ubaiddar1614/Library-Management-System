import com.library.exception.BookAlreadyBorrowedException;
import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidBookDataException;
import com.library.exception.MemberNotFoundException;
import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.repository.TransactionRepository;
import com.library.service.BookService;
import com.library.service.MemberService;
import com.library.service.TransactionsService;
import com.library.util.DateUtil;
import com.library.util.FileHandler;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private BookService bookService;
    private MemberService memberService;
    private TransactionsService transactionService;
    private Scanner scanner;
    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    private TransactionRepository transactionRepository;

    public LibraryManagementSystem() {
        // Initialize repositories
        bookRepository = new BookRepository();
        memberRepository = new MemberRepository();
        transactionRepository = new TransactionRepository();

        // Initialize services
        bookService = new BookService(bookRepository);
        memberService = new MemberService(memberRepository);
        transactionService = new TransactionsService(transactionRepository, bookService, memberService);

        // Initialize scanner for user input
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        System.out.println("===================================");
        System.out.println("     LIBRARY MANAGEMENT SYSTEM     ");
        System.out.println("===================================");

        // Check if we need to load sample data (only if no data exists)
        if (bookService.getAllBooks().isEmpty() && memberService.getAllMembers().isEmpty()) {
            boolean loadSamples = getYesNoInput("No existing data found. Would you like to load sample data? (y/n): ");
            if (loadSamples) {
                loadSampleData();
            }
        } else {
            System.out.println("Loaded existing data from files.");
        }

        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleBookManagement();
                    break;
                case 2:
                    handleMemberManagement();
                    break;
                case 3:
                    handleTransactionManagement();
                    break;
                case 4:
                    handleDataManagement();
                    break;
                case 5:
                    saveAllData();
                    running = false;
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("1. Book Management");
        System.out.println("2. Member Management");
        System.out.println("3. Transaction Management");
        System.out.println("4. Data Management");
        System.out.println("5. Save & Exit");
    }

    private void handleBookManagement() {
        boolean running = true;

        while (running) {
            System.out.println("\nBOOK MANAGEMENT");
            System.out.println("1. Add a new book");
            System.out.println("2. View all books");
            System.out.println("3. Search books by title");
            System.out.println("4. Search books by author");
            System.out.println("5. View book details");
            System.out.println("6. Update book information");
            System.out.println("7. Remove a book");
            System.out.println("8. Return to main menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchBooksByTitle();
                    break;
                case 4:
                    searchBooksByAuthor();
                    break;
                case 5:
                    viewBookDetails();
                    break;
                case 6:
                    updateBook();
                    break;
                case 7:
                    removeBook();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void handleMemberManagement() {
        boolean running = true;

        while (running) {
            System.out.println("\nMEMBER MANAGEMENT");
            System.out.println("1. Register a new member");
            System.out.println("2. View all members");
            System.out.println("3. Search members by name");
            System.out.println("4. View member details");
            System.out.println("5. Update member information");
            System.out.println("6. Remove a member");
            System.out.println("7. Return to main menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerMember();
                    break;
                case 2:
                    viewAllMembers();
                    break;
                case 3:
                    searchMembersByName();
                    break;
                case 4:
                    viewMemberDetails();
                    break;
                case 5:
                    updateMember();
                    break;
                case 6:
                    removeMember();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void handleTransactionManagement() {
        boolean running = true;

        while (running) {
            System.out.println("\nTRANSACTION MANAGEMENT");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. View all transactions");
            System.out.println("4. View active transactions");
            System.out.println("5. View overdue transactions");
            System.out.println("6. Return to main menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    viewAllTransactions();
                    break;
                case 4:
                    viewActiveTransactions();
                    break;
                case 5:
                    viewOverdueTransactions();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void handleDataManagement() {
        boolean running = true;

        while (running) {
            System.out.println("\nDATA MANAGEMENT");
            System.out.println("1. Save all data");
            System.out.println("2. Backup data");
            System.out.println("3. Restore from backup");
            System.out.println("4. Return to main menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    saveAllData();
                    break;
                case 2:
                    backupData();
                    break;
                case 3:
                    restoreFromBackup();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void saveAllData() {
        bookRepository.saveToFile();
        memberRepository.saveToFile();
        transactionRepository.saveToFile();
        System.out.println("All data saved successfully.");
    }

    private void backupData() {
        try {
            String backupDir = "library_backup_" + DateUtil.formatDate(LocalDate.now()).replace("-", "");
            File directory = new File(backupDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Copy files from library_data to backup directory
            File dataDir = new File("library_data");
            if (dataDir.exists() && dataDir.isDirectory()) {
                File[] files = dataDir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        java.nio.file.Files.copy(
                                file.toPath(),
                                new File(backupDir + "/" + file.getName()).toPath(),
                                java.nio.file.StandardCopyOption.REPLACE_EXISTING
                        );
                    }
                    System.out.println("Backup created successfully in directory: " + backupDir);
                }
            } else {
                System.out.println("No data to backup.");
            }
        } catch (Exception e) {
            System.err.println("Error creating backup: " + e.getMessage());
        }
    }

    private void restoreFromBackup() {
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter backup directory name (e.g., library_backup_20250505): ");
        String backupDir = scanner.nextLine();

        File directory = new File(backupDir);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Backup directory not found.");
            return;
        }

        try {
            // Ensure data directory exists
            FileHandler.ensureDataDirectoryExists();

            // Copy files from backup directory to library_data
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    java.nio.file.Files.copy(
                            file.toPath(),
                            new File("library_data/" + file.getName()).toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING
                    );
                }
                System.out.println("Data restored successfully from: " + backupDir);
                System.out.println("Please restart the application to load the restored data.");
            }
        } catch (Exception e) {
            System.err.println("Error restoring from backup: " + e.getMessage());
        }
    }

    // Book management methods
    private void addBook() {
        System.out.println("\nADD NEW BOOK");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        LocalDate publishDate = null;
        while (publishDate == null) {
            System.out.print("Enter publish date (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine();
            try {
                publishDate = DateUtil.parseDate(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        try {
            Book book = bookService.addBook(title, author, isbn, genre, publishDate);
            System.out.println("Book added successfully! Book ID: " + book.getId());
        } catch (InvalidBookDataException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateBook() {
        System.out.println("\nUPDATE BOOK INFORMATION");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter book ID to update: ");
        String bookId = scanner.nextLine();

        try {
            Book book = bookService.getBookById(bookId);
            System.out.println("Current book information:");
            System.out.println(book);

            System.out.print("Enter new title (leave blank to keep current): ");
            String title = scanner.nextLine();
            if (!title.isEmpty()) {
                book.setTitle(title);
            }

            System.out.print("Enter new author (leave blank to keep current): ");
            String author = scanner.nextLine();
            if (!author.isEmpty()) {
                book.setAuthor(author);
            }

            System.out.print("Enter new ISBN (leave blank to keep current): ");
            String isbn = scanner.nextLine();
            if (!isbn.isEmpty()) {
                book.setIsbn(isbn);
            }

            System.out.print("Enter new genre (leave blank to keep current): ");
            String genre = scanner.nextLine();
            if (!genre.isEmpty()) {
                book.setGenre(genre);
            }

            bookService.updateBook(book);
            System.out.println("Book information updated successfully!");
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void removeBook() {
        System.out.println("\nREMOVE BOOK");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter book ID to remove: ");
        String bookId = scanner.nextLine();

        try {
            bookService.removeBook(bookId);
            System.out.println("Book removed successfully!");
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllBooks() {
        System.out.println("\nALL BOOKS");
        List<Book> books = bookService.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books found in the library.");
            return;
        }

        displayBooks(books);
    }

    private void searchBooksByTitle() {
        System.out.println("\nSEARCH BOOKS BY TITLE");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter title to search: ");
        String title = scanner.nextLine();

        List<Book> books = bookService.searchBooksByTitle(title);

        if (books.isEmpty()) {
            System.out.println("No books found with title containing: " + title);
            return;
        }

        System.out.println("Found " + books.size() + " book(s):");
        displayBooks(books);
    }

    private void searchBooksByAuthor() {
        System.out.println("\nSEARCH BOOKS BY AUTHOR");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter author name to search: ");
        String author = scanner.nextLine();

        List<Book> books = bookService.searchBooksByAuthor(author);

        if (books.isEmpty()) {
            System.out.println("No books found with author containing: " + author);
            return;
        }

        System.out.println("Found " + books.size() + " book(s):");
        displayBooks(books);
    }

    private void viewBookDetails() {
        System.out.println("\nVIEW BOOK DETAILS");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter book ID: ");
        String id = scanner.nextLine();

        try {
            Book book = bookService.getBookById(id);
            System.out.println("\nBook Details:");
            System.out.println(book);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void displayBooks(List<Book> books) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.printf("%-10s %-30s %-20s %-15s %-10s\n", "ID", "Title", "Author", "Genre", "Available");
        System.out.println("--------------------------------------------------------------------------------");

        for (Book book : books) {
            System.out.printf("%-10s %-30s %-20s %-15s %-10s\n",
                    book.getId(),
                    truncateString(book.getTitle(), 27),
                    truncateString(book.getAuthor(), 17),
                    truncateString(book.getGenre(), 12),
                    book.isAvailable() ? "Yes" : "No");
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    // Member management methods
    private void registerMember() {
        System.out.println("\nREGISTER NEW MEMBER");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter contact information: ");
        String contactInfo = scanner.nextLine();

        Member member = memberService.registerMember(name, contactInfo);
        System.out.println("Member registered successfully! Member ID: " + member.getId());
    }

    private void updateMember() {
        System.out.println("\nUPDATE MEMBER INFORMATION");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter member ID to update: ");
        String memberId = scanner.nextLine();

        try {
            Member member = memberService.getMemberById(memberId);
            System.out.println("Current member information:");
            System.out.println(member);

            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                member.setName(name);
            }

            System.out.print("Enter new contact information (leave blank to keep current): ");
            String contactInfo = scanner.nextLine();
            if (!contactInfo.isEmpty()) {
                member.setContactInfo(contactInfo);
            }

            memberService.updateMember(member);
            System.out.println("Member information updated successfully!");
        } catch (MemberNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void removeMember() {
        System.out.println("\nREMOVE MEMBER");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter member ID to remove: ");
        String memberId = scanner.nextLine();

        try {
            memberService.removeMember(memberId);
            System.out.println("Member removed successfully!");
        } catch (MemberNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllMembers() {
        System.out.println("\nALL MEMBERS");
        List<Member> members = memberService.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("No members registered in the system.");
            return;
        }

        displayMembers(members);
    }

    private void searchMembersByName() {
        System.out.println("\nSEARCH MEMBERS BY NAME");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        List<Member> members = memberService.searchMembersByName(name);

        if (members.isEmpty()) {
            System.out.println("No members found with name containing: " + name);
            return;
        }

        System.out.println("Found " + members.size() + " member(s):");
        displayMembers(members);
    }

    private void viewMemberDetails() {
        System.out.println("\nVIEW MEMBER DETAILS");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter member ID: ");
        String id = scanner.nextLine();

        try {
            Member member = memberService.getMemberById(id);
            System.out.println("\nMember Details:");
            System.out.println(member);

            if (!member.getBorrowedBooks().isEmpty()) {
                System.out.println("\nCurrently Borrowed Books:");
                for (Book book : member.getBorrowedBooks()) {
                    System.out.println("- " + book.getTitle() + " (ID: " + book.getId() + ")");
                }
            } else {
                System.out.println("\nNo currently borrowed books.");
            }
        } catch (MemberNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void displayMembers(List<Member> members) {
        System.out.println("\n------------------------------------------------------------");
        System.out.printf("%-10s %-25s %-25s %-5s\n", "ID", "Name", "Contact Info", "Books");
        System.out.println("------------------------------------------------------------");

        for (Member member : members) {
            System.out.printf("%-10s %-25s %-25s %-5d\n",
                    member.getId(),
                    truncateString(member.getName(), 22),
                    truncateString(member.getContactInfo(), 22),
                    member.getBorrowedBooks().size());
        }
        System.out.println("------------------------------------------------------------");
    }

    // Transaction management methods
    private void borrowBook() {
        System.out.println("\nBORROW A BOOK");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();

        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        try {
            Transaction transaction = transactionService.borrowBook(bookId, memberId);
            System.out.println("Book borrowed successfully!");
            System.out.println("Transaction ID: " + transaction.getId());
            System.out.println("Due Date: " + DateUtil.formatDate(transaction.getDueDate()));
        } catch (BookNotFoundException | MemberNotFoundException | BookAlreadyBorrowedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void returnBook() {
        System.out.println("\nRETURN A BOOK");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();

        try {
            Transaction transaction = transactionService.returnBook(bookId);
            System.out.println("Book returned successfully!");
            System.out.println("Transaction ID: " + transaction.getId());

            LocalDate dueDate = transaction.getDueDate();
            LocalDate returnDate = transaction.getReturnDate();

            if (returnDate.isAfter(dueDate)) {
                int daysLate = DateUtil.daysBetween(dueDate, returnDate);
                System.out.println("Book returned " + daysLate + " day(s) late.");
            } else {
                System.out.println("Book returned on time.");
            }
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllTransactions() {
        System.out.println("\nALL TRANSACTIONS");
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found in the system.");
            return;
        }

        displayTransactions(transactions);
    }

    private void viewActiveTransactions() {
        System.out.println("\nACTIVE TRANSACTIONS");
        List<Transaction> transactions = transactionService.getActiveTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No active transactions found in the system.");
            return;
        }

        displayTransactions(transactions);
    }

    private void viewOverdueTransactions() {
        System.out.println("\nOVERDUE TRANSACTIONS");
        List<Transaction> transactions = transactionService.getOverdueTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No overdue transactions found in the system.");
            return;
        }

        displayTransactions(transactions);
    }

    private void displayTransactions(List<Transaction> transactions) {
        System.out.println("\n--------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-12s %-12s %-10s\n",
                "ID", "Book Title", "Member Name", "Borrow Date", "Due Date", "Status");
        System.out.println("--------------------------------------------------------------------------------------");

        for (Transaction transaction : transactions) {
            String status;
            if (transaction.isReturned()) {
                status = "Returned";
            } else if (transaction.isOverdue()) {
                status = "Overdue";
            } else {
                status = "Active";
            }

            System.out.printf("%-10s %-20s %-20s %-12s %-12s %-10s\n",
                    transaction.getId(),
                    truncateString(transaction.getBook().getTitle(), 17),
                    truncateString(transaction.getMember().getName(), 17),
                    DateUtil.formatDate(transaction.getBorrowDate()),
                    DateUtil.formatDate(transaction.getDueDate()),
                    status);
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    // Helper methods
    private int getIntInput(String prompt) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt);

            try {
                value = scanner.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        return value;
    }

    private boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Please enter 'y' or 'n'.");
        }
    }

    private String truncateString(String str, int maxLength) {
        if (str == null) {
            return "";
        }

        if (str.length() <= maxLength) {
            return str;
        }

        return str.substring(0, maxLength - 3) + "...";
    }

    // Load sample data for testing
    private void loadSampleData() {
        try {
            // Add sample books
            bookService.addBook("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Fiction",
                    LocalDate.of(1925, 4, 10));
            bookService.addBook("To Kill a Mockingbird", "Harper Lee", "9780061120084", "Fiction",
                    LocalDate.of(1960, 7, 11));
            bookService.addBook("1984", "George Orwell", "9780451524935", "Science Fiction",
                    LocalDate.of(1949, 6, 8));
            bookService.addBook("The Hobbit", "J.R.R. Tolkien", "9780547928227", "Fantasy",
                    LocalDate.of(1937, 9, 21));
            bookService.addBook("Effective Java", "Joshua Bloch", "9780134685991", "Programming",
                    LocalDate.of(2018, 1, 6));

            // Add sample members
            memberService.registerMember("John Smith", "john.smith@example.com");
            memberService.registerMember("Jane Doe", "jane.doe@example.com");
            memberService.registerMember("Robert Johnson", "robert.johnson@example.com");

            System.out.println("Sample data loaded successfully!");
        } catch (InvalidBookDataException e) {
            System.out.println("Error loading sample data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        lms.start();
    }
}