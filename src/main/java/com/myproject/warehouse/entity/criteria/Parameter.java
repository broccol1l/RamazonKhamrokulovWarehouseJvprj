package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Product;

public interface Parameter<P extends Product> {

    boolean test(P product);

    static <P extends Product> Parameter<P> any() {
        return product -> true;
    }
}