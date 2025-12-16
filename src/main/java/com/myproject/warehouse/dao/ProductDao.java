package com.myproject.warehouse.dao;

import com.myproject.warehouse.entity.Product;
import com.myproject.warehouse.entity.criteria.SearchCriteria;
import com.myproject.warehouse.dao.exception.DaoException;
import java.util.List;

public interface ProductDao<P extends Product> {

    List<P> find(SearchCriteria<P> criteria) throws DaoException;
}