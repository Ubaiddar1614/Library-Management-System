package com.library.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class Member implements Serializable

{
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String contactInfo;
    private LocalDate joindate;
    private List<Book> borrowedBooks;


    public Member(String id , String name, String contactInfo)
    {
        this.id=id;
        this.name=name;
        this.contactInfo=contactInfo;
        this.joindate=LocalDate.now();
        this.borrowedBooks=new ArrayList<>();
    }
    //getters and setters


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }
    public LocalDate getJoindate() {
        return joindate;
    }
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }


    public String toString()
    {
        return " ID:"+id + ", Name"+name+",Contact"+contactInfo
                +",Joined "+joindate+",Books Borrowed"+borrowedBooks.size();
    }
}
