package ru.skillbox;

public class Book {
    private final String name;
    private final String author;
    private final int pageCount;
    private final int isbn;

    public Book(String name, String author, int pageCount, int isbn) {
        this.name = name;
        this.author = author;
        this.pageCount = pageCount;
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getIsbn() {
        return isbn;
    }
}
