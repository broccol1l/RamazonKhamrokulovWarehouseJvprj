package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Product;

public record QuantityParameter(Range<Integer> range) implements Parameter<Product> {

    public QuantityParameter(Integer exact) {
        this(new Range<>(exact, exact));
    }

    @Override
    public boolean test(Product product) {
        return range.isIn(product.getQuantity());
    }
}