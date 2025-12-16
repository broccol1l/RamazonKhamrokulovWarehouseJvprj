package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Magazine;
import com.myproject.warehouse.entity.Product;

public record IssueNumberParameter(Range<Integer> range) implements Parameter<Product> {

    public IssueNumberParameter(Integer exact) {
        this(new Range<>(exact, exact));
    }

    @Override
    public boolean test(Product product) {
        if (product instanceof Magazine magazine) {
            return range.isIn(magazine.getIssueNumber());
        }
        return false;
    }
}