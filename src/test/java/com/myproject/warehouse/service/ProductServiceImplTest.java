package com.myproject.warehouse.service;

import com.myproject.warehouse.AppFactory;

import com.myproject.warehouse.entity.Product;
import com.myproject.warehouse.entity.criteria.ProductSearchCriteria;
import com.myproject.warehouse.entity.criteria.SearchCriteria;
import com.myproject.warehouse.entity.criteria.SortParameter;
import com.myproject.warehouse.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductServiceImplTest {

    @Test
    void shouldFindAndSortProductsUsingService() {

        ProductService service = AppFactory.getInstance().getProductService();

        try {
            SearchCriteria<Product> criteria = new ProductSearchCriteria<>();
            criteria.add(new SortParameter("price"));

            List<Product> products = service.find(criteria);

            Assertions.assertNotNull(products);
            Assertions.assertEquals(4, products.size());

            Assertions.assertEquals("Vogue", products.get(0).getName());
            Assertions.assertEquals("War and Peace", products.get(3).getName());


        } catch (ServiceException e) {
            Assertions.fail("ServiceException was thrown", e);
        }
    }
}