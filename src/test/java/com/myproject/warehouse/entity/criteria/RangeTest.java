package com.myproject.warehouse.entity.criteria;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RangeTest {

    @Test
    void shouldThrowExceptionWhenFromIsGreaterThanTo() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Range<>(50.0, 10.0);
        });
    }

    @Test
    void shouldIncludeBoundaryValues() {
        Range<Integer> range = new Range<>(10, 20);

        Assertions.assertTrue(range.isIn(10), "Should include 10");
        Assertions.assertTrue(range.isIn(20), "Should include 20");
    }

    @Test
    void shouldExcludeValuesOutsideRange() {
        Range<Integer> range = new Range<>(10, 20);

        Assertions.assertFalse(range.isIn(9), "Should exclude 9");
        Assertions.assertFalse(range.isIn(21), "Should exclude 21");
    }

    @Test
    void shouldHandleNullBoundary() {
        Range<Double> rangeFrom = new Range<>(10.0, null);
        Assertions.assertTrue(rangeFrom.isIn(100.0), "Should include value greater than 10");

        Range<Double> rangeTo = new Range<>(null, 50.0);
        Assertions.assertTrue(rangeTo.isIn(1.0), "Should include value less than 50");
    }
}