package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Book;
import com.myproject.warehouse.entity.Product;

public record PageCountParameter(Range<Integer> range) implements Parameter<Product> {

    public PageCountParameter(Integer exact) {
        this(new Range<>(exact, exact));
    }

    @Override
    public boolean test(Product product) {
        if (product instanceof Book book) {
            return range.isIn(book.getPageCount());
        }
        return false;
    }
}