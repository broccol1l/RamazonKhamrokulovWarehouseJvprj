package com.myproject.warehouse.entity.criteria;

import com.myproject.warehouse.entity.Book;
import com.myproject.warehouse.entity.Magazine;
import com.myproject.warehouse.entity.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchCriteria<P extends Product> implements SearchCriteria<P> {

    private final List<Parameter<? extends P>> parameters = new ArrayList<>();

    @Override
    public SearchCriteria<P> add(Parameter<? extends P> parameter) {
        if (parameter != null) {
            this.parameters.add(parameter);
        }
        return this;
    }

    public List<Parameter<? extends P>> getParameters() {
        return parameters;
    }


    @Override
    public boolean test(P product) {
        if (parameters.isEmpty()) {
            return true;
        }

        for (Parameter<? extends P> param : parameters) {

            if (param instanceof NameParameter p) {
                if (!p.test(product)) return false;
            }
            else if (param instanceof PriceParameter p) {
                if (!p.test(product)) return false;
            }
            else if (param instanceof QuantityParameter p) {
                if (!p.test(product)) return false;
            }
            else if (param instanceof SortParameter p) {
                if (!p.test(product)) return false;
            }

            else if (param instanceof AuthorParameter p) {
                if (product instanceof Book b) {
                    if (!p.test(b)) return false;
                } else {
                    return false;
                }
            }
            else if (param instanceof PageCountParameter p) {
                if (product instanceof Book b) {
                    if (!p.test(b)) return false;
                } else {
                    return false;
                }
            }

            else if (param instanceof PublisherParameter p) {
                if (product instanceof Magazine m) {
                    if (!p.test(m)) return false;
                } else {
                    return false;
                }
            }
            else if (param instanceof IssueNumberParameter p) {
                if (product instanceof Magazine m) {
                    if (!p.test(m)) return false;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}