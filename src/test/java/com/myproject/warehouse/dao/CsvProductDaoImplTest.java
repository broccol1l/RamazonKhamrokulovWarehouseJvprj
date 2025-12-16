package com.myproject.warehouse.dao;

import com.myproject.warehouse.dao.exception.DaoException;
import com.myproject.warehouse.dao.impl.CsvProductDaoImpl;
import com.myproject.warehouse.entity.Product;

import com.myproject.warehouse.entity.criteria.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CsvProductDaoImplTest {

    private static ProductDao<Product> dao;

    @BeforeAll
    static void setUp() {
        dao = new CsvProductDaoImpl("books-test.csv", "magazines-test.csv");
    }

    @Test
    void shouldFindAllProducts() throws DaoException {
        SearchCriteria<Product> criteria = new ProductSearchCriteria<>();
        List<Product> products = dao.find(criteria);
        Assertions.assertEquals(4, products.size());
    }

    @Test
    void shouldFindByAuthor() throws DaoException {
        SearchCriteria<Product> criteria = new ProductSearchCriteria<>();
        criteria.add(new AuthorParameter("Tolstoy"));
        List<Product> products = dao.find(criteria);
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals("War and Peace", products.get(0).getName());
    }

    @Test
    void shouldFindByPublisher() throws DaoException {
        SearchCriteria<Product> criteria = new ProductSearchCriteria<>();
        criteria.add(new PublisherParameter("Conde Nast"));
        List<Product> products = dao.find(criteria);
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals("Vogue", products.get(0).getName());
    }

    @Test
    void shouldFindByPriceRange() throws DaoException {
        SearchCriteria<Product> criteria = new ProductSearchCriteria<>();
        criteria.add(new PriceParameter(new Range<>(20.0, 60.0)));
        List<Product> products = dao.find(criteria);
        Assertions.assertEquals(3, products.size());
    }

    @Test
    void shouldReturnEmptyListForNoMatch() throws DaoException {
        SearchCriteria<Product> criteria = new ProductSearchCriteria<>();
        criteria.add(new AuthorParameter("Pushkin"));
        List<Product> products = dao.find(criteria);
        Assertions.assertTrue(products.isEmpty());
    }

    @Test
    void shouldThrowDaoExceptionWhenFileIsMissing() {
        ProductDao<Product> badDao = new CsvProductDaoImpl("nonexistent_books.csv", "magazines-test.csv");

        Assertions.assertThrows(DaoException.class, () -> {
            badDao.find(new ProductSearchCriteria<>());
        });
    }
}