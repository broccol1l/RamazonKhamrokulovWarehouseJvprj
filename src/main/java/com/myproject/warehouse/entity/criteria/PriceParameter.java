package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Product;

public record PriceParameter(Range<Double> range) implements Parameter<Product> {


    public PriceParameter(Double exactPrice) {
        this(new Range<>(exactPrice, exactPrice));
    }

    @Override
    public boolean test(Product product) {
        return range.isIn(product.getPrice());
    }
}