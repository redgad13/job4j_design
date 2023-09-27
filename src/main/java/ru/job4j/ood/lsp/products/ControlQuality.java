package ru.job4j.ood.lsp.products;

import java.util.Date;
import java.util.List;

public class ControlQuality {
    private List<AbstractStore> stores;

    public ControlQuality(List<AbstractStore> stores) {
        this.stores = stores;
    }

    public List<AbstractStore> actionOnExpiryPercent(List<Food> foods, Date date) {
        BestBeforeDate bbd = new BestBeforeDate();
        List<Food> foodsWithDatesTillExpiry = bbd.findPercentTillExpiryDate(foods, date.getTime());
        for (AbstractStore store : stores) {
            store.execute(foodsWithDatesTillExpiry);
        }
        return stores;
    }
}
