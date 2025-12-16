package com.myproject.warehouse;

import com.myproject.warehouse.view.View;

public class Main {

    public static void main(String[] args) {
        View view = null;
        try {
            view = AppFactory.getInstance().getView();

            view.start();

        } catch (Exception e) {
            if (view != null) {
                view.crush();
            }
        }
    }
}