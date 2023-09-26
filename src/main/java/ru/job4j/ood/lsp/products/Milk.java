package ru.job4j.ood.lsp.products;

import java.util.Date;

public class Milk extends Food {

    public Milk(String name, Date createDate, Date expiryDate) {
        super(name, createDate, expiryDate);
    }

    public Milk(String name, Date createDate, Date expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
