package com.myproject.warehouse;

import com.myproject.warehouse.controller.Controller;
import com.myproject.warehouse.controller.ControllerImpl;
import com.myproject.warehouse.dao.ProductDao;
import com.myproject.warehouse.dao.impl.CsvProductDaoImpl;
import com.myproject.warehouse.entity.Product;
import com.myproject.warehouse.service.ProductService;
import com.myproject.warehouse.service.impl.ProductServiceImpl;
import com.myproject.warehouse.view.ConsoleViewImpl;
import com.myproject.warehouse.view.View;

public class AppFactory {

    private static final AppFactory instance = new AppFactory();

    private final ProductDao<Product> productDao = new CsvProductDaoImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final Controller controller = new ControllerImpl();
    private final View view = new ConsoleViewImpl();

    private AppFactory() {}

    public static AppFactory getInstance() {
        return instance;
    }

    public ProductDao<Product> getProductDao() {
        return productDao;
    }

    public ProductService getProductService() {
        return productService;
    }

    public Controller getController() {
        return controller;
    }

    public View getView() {
        return view;
    }
}