package com.myproject.warehouse.entity;

import java.util.Objects;

public class Book extends Product {

    private String author;
    private int pageCount;

    public Book() {
        super();
    }

    public Book(String name, double price, int quantity, String author, int pageCount) {
        super(name, price, quantity);
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return pageCount == book.pageCount &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, pageCount);
    }

    @Override
    public String toString() {
        return "Book{" +
                super.toString() +
                ", author='" + author + '\'' +
                ", pageCount=" + pageCount +
                '}';
    }
}