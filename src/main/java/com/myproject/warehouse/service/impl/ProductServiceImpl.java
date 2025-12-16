package com.myproject.warehouse.service.impl;

import com.myproject.warehouse.AppFactory;

import com.myproject.warehouse.dao.ProductDao;
import com.myproject.warehouse.dao.exception.DaoException;
import com.myproject.warehouse.entity.Book;
import com.myproject.warehouse.entity.Magazine;
import com.myproject.warehouse.entity.Product;

import com.myproject.warehouse.entity.criteria.Parameter;
import com.myproject.warehouse.entity.criteria.ProductSearchCriteria;
import com.myproject.warehouse.entity.criteria.SearchCriteria;
import com.myproject.warehouse.entity.criteria.SortParameter;
import com.myproject.warehouse.service.ProductService;
import com.myproject.warehouse.service.exception.ServiceException;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> find(SearchCriteria<Product> criteria) throws ServiceException {
        String sortBy = extractSortBy(criteria);

        try {
            ProductDao<Product> productDao = AppFactory.getInstance().getProductDao();
            List<Product> products = productDao.find(criteria);

            if (sortBy != null) {
                sortProducts(products, sortBy);
            }

            return products;
        } catch (DaoException e) {
            throw new ServiceException("Failed to find products in DAO", e);
        }
    }

    private String extractSortBy(SearchCriteria<Product> criteria) {
        String sortBy = null;

        if (criteria instanceof ProductSearchCriteria<Product> psc) {

            Iterator<Parameter<? extends Product>> iterator = psc.getParameters().iterator();

            while (iterator.hasNext()) {
                Parameter<? extends Product> param = iterator.next();
                if (param instanceof SortParameter sortParam) {
                    sortBy = sortParam.sortBy();
                    iterator.remove();
                    break;
                }
            }
        }
        return sortBy;
    }

    private void sortProducts(List<Product> products, String sortBy) {
        Comparator<Product> comparator = null;

        switch (sortBy.toLowerCase()) {
            case "name":
                comparator = Comparator.comparing(Product::getName);
                break;
            case "price":
                comparator = Comparator.comparingDouble(Product::getPrice);
                break;
            case "quantity":
                comparator = Comparator.comparingInt(Product::getQuantity);
                break;
            case "author":
                comparator = Comparator.comparing(product -> {
                    if (product instanceof Book) return ((Book) product).getAuthor();
                    return null;
                }, Comparator.nullsLast(String::compareToIgnoreCase));
                break;
            case "publisher":
                comparator = Comparator.comparing(product -> {
                    if (product instanceof Magazine) return ((Magazine) product).getPublisher();
                    return null;
                }, Comparator.nullsLast(String::compareToIgnoreCase));
                break;
            case "pagecount":
                comparator = Comparator.comparing(product -> {
                    if (product instanceof Book) return ((Book) product).getPageCount();
                    return Integer.MAX_VALUE;
                });
                break;
            case "issuenumber":
                comparator = Comparator.comparing(product -> {
                    if (product instanceof Magazine) return ((Magazine) product).getIssueNumber();
                    return Integer.MAX_VALUE;
                });
                break;
        }

        if (comparator != null) {
            products.sort(comparator);
        }
    }
}