package com.library.util;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.repository.TransactionRepository;
import com.library.service.BookService;
import com.library.service.MemberService;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String DATA_DIR = "library_data";
    private static final String BOOKS_FILE = DATA_DIR + "/books.dat";
    private static final String MEMBERS_FILE = DATA_DIR + "/members.dat";
    private static final String TRANSACTIONS_FILE = DATA_DIR + "/transactions.dat";

    public static void ensureDataDirectoryExists() {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Created data directory: " + DATA_DIR);
            } else {
                System.err.println("Failed to create data directory: " + DATA_DIR);
            }
        }
    }

    public static void saveBooks(List<Book> books) {
        ensureDataDirectoryExists();
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
            System.out.println("Books saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }

    public static void saveMembers(List<Member> members) {
        ensureDataDirectoryExists();
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(MEMBERS_FILE))) {
            oos.writeObject(members);
            System.out.println("Members saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving members: " + e.getMessage());
        }
    }

    public static void saveTransactions(List<Transaction> transactions) {
        ensureDataDirectoryExists();
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(TRANSACTIONS_FILE))) {
            oos.writeObject(transactions);
            System.out.println("Transactions saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving transactions: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Book> loadBooks() {
        File file = new File(BOOKS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {
            List<Book> books = (List<Book>) ois.readObject();
            System.out.println("Loaded " + books.size() + " books from file.");
            return books;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading books: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Member> loadMembers() {
        File file = new File(MEMBERS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {
            List<Member> members = (List<Member>) ois.readObject();
            System.out.println("Loaded " + members.size() + " members from file.");
            return members;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading members: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Transaction> loadTransactions() {
        File file = new File(TRANSACTIONS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {
            List<Transaction> transactions = (List<Transaction>) ois.readObject();
            System.out.println("Loaded " + transactions.size() + " transactions from file.");
            return transactions;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Save all data at once
    public static void saveAllData(BookRepository bookRepository,
                                   MemberRepository memberRepository,
                                   TransactionRepository transactionRepository) {
        saveBooks(bookRepository.getAllBooks());
        saveMembers(memberRepository.getAllMembers());
        saveTransactions(transactionRepository.getAllTransactions());
        System.out.println("All data saved successfully.");
    }
}