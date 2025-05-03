package com.library.repository;
import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository
{
    private Map<String, Transaction> transactions;

    public TransactionRepository() {
        this.transactions = new HashMap<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
    }

    public Transaction getTransactionById(String id) {
        return transactions.get(id);
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions.values());
    }

    public List<Transaction> getActiveTransactions() {
        List<Transaction> activeTransactions = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (!transaction.isReturned()) {
                activeTransactions.add(transaction);
            }
        }
        return activeTransactions;
    }

    public List<Transaction> getOverdueTransactions() {
        List<Transaction> overdueTransactions = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.isOverdue()) {
                overdueTransactions.add(transaction);
            }
        }
        return overdueTransactions;
    }

    public List<Transaction> getTransactionsByMember(Member member) {
        List<Transaction> memberTransactions = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getMember().getId().equals(member.getId())) {
                memberTransactions.add(transaction);
            }
        }
        return memberTransactions;
    }

    public List<Transaction> getTransactionsByBook(Book book) {
        List<Transaction> bookTransactions = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getBook().getId().equals(book.getId())) {
                bookTransactions.add(transaction);
            }
        }
        return bookTransactions;
    }

    public Transaction findActiveTransactionByBook(Book book) {
        for (Transaction transaction : transactions.values()) {
            if (transaction.getBook().getId().equals(book.getId()) && !transaction.isReturned()) {
                return transaction;
            }
        }
        return null;
    }
}
