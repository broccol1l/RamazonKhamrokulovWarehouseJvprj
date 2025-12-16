package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Product;

public record SortParameter(String sortBy) implements Parameter<Product> {

    @Override
    public boolean test(Product product) {
        return true;
    }
}