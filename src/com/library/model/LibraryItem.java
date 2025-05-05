package com.library.model;
import java.time.LocalDate;
import java.io.Serializable;
public abstract class LibraryItem implements Serializable

{
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private LocalDate publishDate;
    private boolean isAvailable;

    LibraryItem( String id , String title, LocalDate publishDate)
    {
        this.id=id;
        this.title=title;
        this.publishDate=publishDate;
        this.isAvailable=true;

    }

    //getters and setters

    public String getId() {
        return id;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String toString()
    {
        return "ID "+ id+ " , Title: "+ title+" , Published:"+
                publishDate+" , Available:"+ (isAvailable ? "yes ": "No" );
    }
}
