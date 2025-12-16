package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Magazine;

public record PublisherParameter(String publisher) implements Parameter<Magazine> {

    @Override
    public boolean test(Magazine product) {
        return publisher.equals(product.getPublisher());
    }
}