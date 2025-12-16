package com.myproject.warehouse.entity.criteria;

import java.util.Objects;

public record Range<T extends Comparable<T>>(T from, T to) {

    public Range {
        if (from != null && to != null && from.compareTo(to) > 0) {
            throw new IllegalArgumentException(from + " must be less than " + to);
        }
    }


    public boolean isIn(T value) {
        Objects.requireNonNull(value);
        boolean within = true;
        if (from != null) {
            within = from.compareTo(value) <= 0;
        }
        if (to != null) {
            within &= value.compareTo(to) <= 0;
        }
        return within;
    }
}