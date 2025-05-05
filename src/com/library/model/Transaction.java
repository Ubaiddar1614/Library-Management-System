package com.library.model;
import java.time.LocalDate;
import java.io.Serializable;


public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Book book;
    private Member member;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Transaction(String id, Book book, Member member) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(14);
        this.returnDate = null;

    }
    //getters and setters

    public String getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public boolean isOverdue() {
        if (isReturned()) {
            return false;
        }
        return LocalDate.now().isAfter(dueDate);
    }

    public String toString() {
        return "ID: " + id + ", Book: " + book.getTitle() + ", Member: " + member.getName() +
                ", Borrowed: " + borrowDate + ", Due: " + dueDate +
                ", Returned: " + (returnDate != null ? returnDate : "Not returned") +
                (isOverdue() ? " (OVERDUE)" : "");

    }
}
