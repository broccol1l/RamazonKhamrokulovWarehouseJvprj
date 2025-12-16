package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Product;

public interface SearchCriteria<P extends Product> {
    SearchCriteria<P> add(Parameter<? extends P> parameter);

    boolean test(P product);
}