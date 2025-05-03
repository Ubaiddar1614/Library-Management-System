package com.library.model;
import java.time.LocalDate;
public class Book  extends LibraryItem
{
    private String author;
    private String isbn;
    private String genre;


    public Book(String author, String isbn,String genre , String id , String title , LocalDate publishDate)
    {
        super(id, title, publishDate);
        this.author=author;
        this.isbn=isbn;
        this.genre=genre;
    }
    //getters and  setters

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String toString()
    {
        return super.toString()+" , Author"+ author+", ISBN"+isbn+",Genre"+genre;
    }
}
