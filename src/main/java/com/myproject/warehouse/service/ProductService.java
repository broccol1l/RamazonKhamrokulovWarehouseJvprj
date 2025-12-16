package com.myproject.warehouse.service;

import com.myproject.warehouse.entity.Product;
import com.myproject.warehouse.entity.criteria.SearchCriteria;
import com.myproject.warehouse.service.exception.ServiceException;
import java.util.List;

public interface ProductService {
    List<Product> find(SearchCriteria<Product> criteria) throws ServiceException;
}