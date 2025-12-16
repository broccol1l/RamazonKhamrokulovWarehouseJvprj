package com.myproject.warehouse.entity;

import java.util.Objects;

public class Magazine extends Product {

    private String publisher;
    private int issueNumber;

    public Magazine() {
        super();
    }

    public Magazine(String name, double price, int quantity, String publisher, int issueNumber) {
        super(name, price, quantity);
        this.publisher = publisher;
        this.issueNumber = issueNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return issueNumber == magazine.issueNumber &&
                Objects.equals(publisher, magazine.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publisher, issueNumber);
    }

    @Override
    public String toString() {
        return "Magazine{" +
                super.toString() +
                ", publisher='" + publisher + '\'' +
                ", issueNumber=" + issueNumber +
                '}';
    }
}