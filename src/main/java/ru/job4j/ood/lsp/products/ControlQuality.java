package ru.job4j.ood.lsp.products;

import java.util.Date;
import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public List<Store> actionOnExpiryPercent(List<Food> foods, Date date) {
        BestBeforeDate bbd = new BestBeforeDate();
        List<Food> foodsWithDatesTillExpiry = bbd.findPercentTillExpiryDate(foods, date.getTime());
        for (Store store : stores) {
            store.execute(foodsWithDatesTillExpiry);
        }
        return stores;
    }
}
