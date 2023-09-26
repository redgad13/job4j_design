package ru.job4j.ood.lsp.products;

import java.util.Date;

public class ControlQuality {
    AbstractStore store;

    private double percentTillExpiry(Date createDate, Date expiryDate) {
        long today = new Date().getTime();
        long totalPeriod = expiryDate.getTime() - createDate.getTime();
        long leftPeriod = expiryDate.getTime() - today;
        return (double) leftPeriod / totalPeriod;
    }

    public AbstractStore actionOnExpiryPercent(Food food) {
        double percent = percentTillExpiry(food.getCreateDate(), food.getExpiryDate());
        if (percent < 0.25) {
            store = new Warehouse(food);
        } else if (percent >= 0.25 && percent < 0.75) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() * 0.01);
            store = new Shop(food);
        } else {
            store = new Trash(food);
        }
        return store;
    }
}
