package ru.job4j.ood.lsp.products;

import java.util.Date;

public abstract class Food {
    private String name;
    private Date expiryDate;
    private Date createDate;
    private double price;
    private double discount;
    private double tillExpiry;

    public Food(String name, Date createDate, Date expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
    }

    public Food(String name, Date createDate, Date expiryDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    protected String getName() {
        return name;
    }

    protected Date getExpiryDate() {
        return expiryDate;
    }

    protected Date getCreateDate() {
        return createDate;
    }

    protected double getPrice() {
        return price;
    }

    protected double getDiscount() {
        return discount;
    }

    protected double getTillExpiry() {
        return tillExpiry;
    }

    protected void setPrice(double price) {
        this.price = price;
    }

    public void setTillExpiry(double tillExpiry) {
        this.tillExpiry = tillExpiry;
    }
}
