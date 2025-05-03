package com.library.model;
 import java.time.LocalDate;
public  abstract class  LibraryItem
{
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
    public void setId(String id) {
        this.id = id;
    }

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
