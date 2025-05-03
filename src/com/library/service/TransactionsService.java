package com.library.service;

import com.library.exception.BookAlreadyBorrowedException;
import com.library.exception.BookNotFoundException;
import com.library.exception.MemberNotFoundException;
import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;
import com.library.repository.TransactionRepository;
import com.library.util.IdGenerator;

import java.time.LocalDate;
import java.util.List;

public class TransactionsService {
    private TransactionRepository transactionRepository;
    private BookService bookService;
    private MemberService memberService;
    private IdGenerator idGenerator;

    public TransactionsService(TransactionRepository transactionRepository,
                              BookService bookService,
                              MemberService memberService) {
        this.transactionRepository = transactionRepository;
        this.bookService = bookService;
        this.memberService = memberService;
        this.idGenerator = new IdGenerator("T");
    }

    public Transaction borrowBook(String bookId, String memberId)
            throws BookNotFoundException, MemberNotFoundException, BookAlreadyBorrowedException {
        // Get book and member
        Book book = bookService.getBookById(bookId);
        Member member = memberService.getMemberById(memberId);

        // Check if book is available
        if (!book.isAvailable()) {
            throw new BookAlreadyBorrowedException("Book with ID " + bookId + " is already borrowed");
        }

        // Mark book as borrowed
        bookService.markBookAsBorrowed(book);

        // Update member's borrowed books
        member.borrowBook(book);

        // Create and store transaction
        String id = idGenerator.generateId();
        Transaction transaction = new Transaction(id, book, member);
        transactionRepository.addTransaction(transaction);

        return transaction;
    }

    public Transaction returnBook(String bookId) throws BookNotFoundException {
        // Get book
        Book book = bookService.getBookById(bookId);

        // Find active transaction for this book
        Transaction transaction = transactionRepository.findActiveTransactionByBook(book);
        if (transaction == null) {
            throw new BookNotFoundException("No active borrowing record found for book with ID " + bookId);
        }

        // Mark book as returned
        bookService.markBookAsReturned(book);

        // Update member's borrowed books
        Member member = transaction.getMember();
        member.returnBook(book);

        // Update transaction
        transaction.setReturnDate(LocalDate.now());

        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    public List<Transaction> getActiveTransactions() {
        return transactionRepository.getActiveTransactions();
    }

    public List<Transaction> getOverdueTransactions() {
        return transactionRepository.getOverdueTransactions();
    }

    public List<Transaction> getTransactionsByMember(String memberId) throws MemberNotFoundException {
        Member member = memberService.getMemberById(memberId);
        return transactionRepository.getTransactionsByMember(member);
    }

    public List<Transaction> getTransactionsByBook(String bookId) throws BookNotFoundException {
        Book book = bookService.getBookById(bookId);
        return transactionRepository.getTransactionsByBook(book);
    }
}