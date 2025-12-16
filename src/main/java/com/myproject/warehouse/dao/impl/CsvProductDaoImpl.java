package com.myproject.warehouse.dao.impl;

import com.myproject.warehouse.dao.ProductDao;
import com.myproject.warehouse.dao.exception.DaoException;
import com.myproject.warehouse.entity.Book;
import com.myproject.warehouse.entity.Magazine;
import com.myproject.warehouse.entity.Product;
import com.myproject.warehouse.entity.criteria.SearchCriteria;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvProductDaoImpl implements ProductDao<Product> {

    private final String booksFile;
    private final String magazinesFile;

    public CsvProductDaoImpl() {
        this.booksFile = "books.csv";
        this.magazinesFile = "magazines.csv";
    }

    public CsvProductDaoImpl(String booksFile, String magazinesFile) {
        this.booksFile = booksFile;
        this.magazinesFile = magazinesFile;
    }

    @Override
    public List<Product> find(SearchCriteria<Product> criteria) throws DaoException {
        List<Product> allProducts = new ArrayList<>();

        allProducts.addAll(loadProducts(this.booksFile));
        allProducts.addAll(loadProducts(this.magazinesFile));

        return allProducts.stream()
                .filter(criteria::test)
                .collect(Collectors.toList());
    }

    private List<Product> loadProducts(String csvFile) throws DaoException {
        List<Product> fileProducts = new ArrayList<>();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(csvFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            if (is == null) {
                throw new DaoException("Resource file not found: " + csvFile, null);
            }

            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Product product = parseLine(line, csvFile);
                fileProducts.add(product);
            }

        } catch (Exception e) {
            throw new DaoException("Error reading file: " + csvFile, e);
        }
        return fileProducts;
    }

    private Product parseLine(String line, String csvFile) {
        String[] data = line.split(",");
        try {
            String name = data[0];
            double price = Double.parseDouble(data[1]);
            int quantity = Integer.parseInt(data[2]);

            if (this.booksFile.equals(csvFile)) {
                String author = data[3];
                int pageCount = Integer.parseInt(data[4]);
                return new Book(name, price, quantity, author, pageCount);
            } else if (this.magazinesFile.equals(csvFile)) {
                String publisher = data[3];
                int issueNumber = Integer.parseInt(data[4]);
                return new Magazine(name, price, quantity, publisher, issueNumber);
            } else {
                throw new IllegalArgumentException("Unknown file type for parsing: " + csvFile);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse line: " + line, e);
        }
    }
}