package ru.job4j.ood.lsp.products;

import java.util.List;

public class BestBeforeDate {

    public List<Food> findPercentTillExpiryDate(List<Food> foods, long currentDate) {
        long totalPeriod;
        long leftPeriod;
        for (Food food : foods) {
            totalPeriod = food.getExpiryDate().getTime() - food.getCreateDate().getTime();
            leftPeriod = food.getExpiryDate().getTime() - currentDate;
            double percent = (double) 1L - ((double) leftPeriod / totalPeriod);
            food.setTillExpiry(percent);
        }
        return foods;
    }
}
