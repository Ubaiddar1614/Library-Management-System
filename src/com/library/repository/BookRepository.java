package com.library.repository;

import com.library.model.Book;
import com.library.exception.BookNotFoundException;
import com.library.util.FileHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {
    private Map<String, Book> books;

    public BookRepository() {
        this.books = new HashMap<>();
        loadBooksFromFile();
    }

    private void loadBooksFromFile() {
        List<Book> loadedBooks = FileHandler.loadBooks();
        for (Book book : loadedBooks) {
            books.put(book.getId(), book);
        }
    }

    public void saveToFile() {
        FileHandler.saveBooks(getAllBooks());
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
        saveToFile();
    }

    public Book getBookById(String id) throws BookNotFoundException {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public List<Book> searchBooksByAuthor(String author) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public void updateBook(Book book) throws BookNotFoundException {
        if (!books.containsKey(book.getId())) {
            throw new BookNotFoundException("Book with ID " + book.getId() + " not found");
        }
        books.put(book.getId(), book);
        saveToFile();
    }

    public void removeBook(String id) throws BookNotFoundException {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        books.remove(id);
        saveToFile();
    }
}
