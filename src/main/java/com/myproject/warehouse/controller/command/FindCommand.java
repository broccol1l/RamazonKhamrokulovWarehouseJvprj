package com.myproject.warehouse.controller.command;

import com.myproject.warehouse.AppFactory;

import com.myproject.warehouse.entity.Product;
import com.myproject.warehouse.entity.criteria.*;
import com.myproject.warehouse.service.ProductService;
import com.myproject.warehouse.service.exception.ServiceException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommand implements Command {

    private static final Pattern ARG_PATTERN = Pattern.compile("([^=\\s]+)=(?:\"([^\"]*)\"|([^=\\s]+))");

    @Override
    public String execute(String argsString) {
        SearchCriteria<Product> criteria;
        try {
            criteria = parseCriteria(argsString);

            ProductService productService = AppFactory.getInstance().getProductService();
            List<Product> products = productService.find(criteria);

            return formatResponse(products);

        } catch (ServiceException e) {
            return "Error: " + e.getMessage();
        } catch (IllegalArgumentException e) {
            return "Error: Invalid command arguments. " + e.getMessage();
        }
    }

    private SearchCriteria<Product> parseCriteria(String argsString) {
        SearchCriteria<Product> criteria = new ProductSearchCriteria<>();

        if (argsString.isEmpty()) {
            return criteria;
        }

        Matcher m = ARG_PATTERN.matcher(argsString);
        boolean foundAtLeastOneMatch = false;

        while (m.find()) {
            foundAtLeastOneMatch = true;
            String key = m.group(1);
            String valueStr = (m.group(2) != null) ? m.group(2) : m.group(3);

            switch (key.toUpperCase()) {
                case "NAME":
                    criteria.add(new NameParameter(valueStr));
                    break;
                case "PRICE":
                    criteria.add(parsePriceParameter(valueStr));
                    break;
                case "QUANTITY":
                    criteria.add(new QuantityParameter(parseIntegerRange(valueStr)));
                    break;

                case "AUTHOR":
                    criteria.add(new AuthorParameter(valueStr));
                    break;
                case "PAGECOUNT":
                    criteria.add(new PageCountParameter(parseIntegerRange(valueStr)));
                    break;

                case "PUBLISHER":
                    criteria.add(new PublisherParameter(valueStr));
                    break;
                case "ISSUENUMBER":
                    criteria.add(new IssueNumberParameter(parseIntegerRange(valueStr)));
                    break;

                case "SORT":
                    criteria.add(new SortParameter(valueStr));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown search key: " + key);
            }
        }

        if (!foundAtLeastOneMatch) {
            throw new IllegalArgumentException("Invalid argument format. Expected key=value.");
        }
        return criteria;
    }

    private PriceParameter parsePriceParameter(String valueStr) {
        if (valueStr.contains(";")) {
            String[] parts = valueStr.split(";");
            Double from = Double.parseDouble(parts[0]);
            Double to = Double.parseDouble(parts[1]);
            return new PriceParameter(new Range<>(from, to));
        } else {
            Double exactPrice = Double.parseDouble(valueStr);
            return new PriceParameter(exactPrice);
        }
    }

    private Range<Integer> parseIntegerRange(String valueStr) {
        if (valueStr.contains(";")) {
            String[] parts = valueStr.split(";");
            Integer from = Integer.parseInt(parts[0]);
            Integer to = Integer.parseInt(parts[1]);
            return new Range<>(from, to);
        } else {
            Integer exact = Integer.parseInt(valueStr);
            return new Range<>(exact, exact);
        }
    }

    private String formatResponse(List<Product> products) {
        if (products.isEmpty()) {
            return "No products found.";
        }
        StringBuilder sb = new StringBuilder("Found " + products.size() + " products:\n");
        for (Product p : products) {
            sb.append(" - ").append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}