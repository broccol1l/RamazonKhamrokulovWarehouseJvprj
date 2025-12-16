package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Product;

public record NameParameter(String name) implements Parameter<Product> {
    @Override
    public boolean test(Product product) {
        return name.equals(product.getName());
    }
}