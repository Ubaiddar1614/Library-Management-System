package com.library.service;
import com.library.exception.BookAlreadyBorrowedException;
import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidBookDataException;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.util.IdGenerator;

import java.time.LocalDate;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;
    private IdGenerator idGenerator;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.idGenerator = new IdGenerator("B");
    }

    public Book addBook(String title, String author, String isbn, String genre, LocalDate publishDate)
            throws InvalidBookDataException {
        // Validate input
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidBookDataException("Book title cannot be empty");
        }

        if (author == null || author.trim().isEmpty()) {
            throw new InvalidBookDataException("Author name cannot be empty");
        }

        if (isbn == null || isbn.trim().isEmpty()) {
            throw new InvalidBookDataException("ISBN cannot be empty");
        }

        String id = idGenerator.generateId();
        // Create book with parameters in the correct order
        Book book = new Book(author, isbn, genre, id, title, publishDate);
        bookRepository.addBook(book);
        return book;
    }

    // Rest of the class remains the same
    public Book getBookById(String id) throws BookNotFoundException {
        return bookRepository.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.getAvailableBooks();
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.searchBooksByTitle(title);
    }

    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.searchBooksByAuthor(author);
    }

    public void updateBook(Book book) throws BookNotFoundException {
        bookRepository.updateBook(book);
    }

    public void removeBook(String id) throws BookNotFoundException {
        bookRepository.removeBook(id);
    }

    public void markBookAsBorrowed(Book book) throws BookAlreadyBorrowedException {
        if (!book.isAvailable()) {
            throw new BookAlreadyBorrowedException("Book with ID " + book.getId() + " is already borrowed");
        }
        book.setAvailable(false);
        try {
            bookRepository.updateBook(book);
        } catch (BookNotFoundException e) {
            // This shouldn't happen as we just retrieved the book
            e.printStackTrace();
        }
    }

    public void markBookAsReturned(Book book) {
        book.setAvailable(true);
        try {
            bookRepository.updateBook(book);
        } catch (BookNotFoundException e) {
            // This shouldn't happen as we just retrieved the book
            e.printStackTrace();
        }
    }
}