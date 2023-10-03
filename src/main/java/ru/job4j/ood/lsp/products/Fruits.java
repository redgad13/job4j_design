package ru.job4j.ood.lsp.products;

import java.util.Date;

public class Fruits extends Food {
    public Fruits(String name, Date createDate, Date expiryDate) {
        super(name, createDate, expiryDate);
    }

    public Fruits(String name, Date createDate, Date expiryDate, double price, double discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
