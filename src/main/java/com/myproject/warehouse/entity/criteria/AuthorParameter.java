package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Book;

public record AuthorParameter(String author) implements Parameter<Book> {

    @Override
    public boolean test(Book product) {
        return author.equals(product.getAuthor());
    }
}