package com.library.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Member
{
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

    public void setId(String id) {
        this.id = id;
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

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public LocalDate getJoindate() {
        return joindate;
    }

    public void setJoindate(LocalDate joindate) {
        this.joindate = joindate;
    }
    public String toString()
    {
        return " ID:"+id + ", Name"+name+",Contact"+contactInfo
                +",Joined "+joindate+",Books Borrowed"+borrowedBooks.size();
    }
}
