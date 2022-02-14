package ru.skillbox;

public class Book {
    private final String title;
    private final String author;
    private final int pageCount;
    private final int isbn;

    public Book(String title, String author, int pageCount, int isbn) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
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
