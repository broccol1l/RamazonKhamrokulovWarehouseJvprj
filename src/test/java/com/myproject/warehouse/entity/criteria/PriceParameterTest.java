package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Book;
import com.myproject.warehouse.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PriceParameterTest {

    @Test
    void shouldTestProductWithinRange() {
        Parameter<Product> priceCriteria = new PriceParameter(new Range<>(10.0, 30.0));

        Product testBook = new Book("Test Book", 25.0, 10, "Author", 100);

        Assertions.assertTrue(priceCriteria.test(testBook));
    }

    @Test
    void shouldTestProductOutsideRange() {
        Parameter<Product> priceCriteria = new PriceParameter(new Range<>(10.0, 30.0));

        Product testBook = new Book("Test Book", 50.0, 10, "Author", 100);

        Assertions.assertFalse(priceCriteria.test(testBook));
    }
}