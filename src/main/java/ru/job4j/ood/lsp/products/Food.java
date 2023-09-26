package ru.job4j.ood.lsp.products;

import java.util.Date;

public abstract class Food {
    private String name;
    private Date expiryDate;
    private Date createDate;
    private double price;
    private int discount;

    public Food(String name, Date createDate, Date expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
    }

    public Food(String name, Date createDate, Date expiryDate, double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
