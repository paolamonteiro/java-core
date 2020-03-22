package com.paolamonteiro.core.library.models;

public class Book {
    private Integer id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Boolean equals(Book nextBook) {
        return this.id.equals(nextBook.getId());
    }

    @Override
    public String toString() {
        return "\nBook: " + this.getTitle()
                + "\nAuthor: " + this.getAuthor();
    }
}
