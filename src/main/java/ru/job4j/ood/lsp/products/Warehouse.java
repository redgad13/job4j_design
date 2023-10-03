package ru.job4j.ood.lsp.products;

import java.util.List;

public class Warehouse extends AbstractStore {

    @Override
    public List<Food> execute(List<Food> foods) {
        for (Food food : foods) {
            if (food.getTillExpiry() < twentyFive) {
                list.add(food);
            }
        }
        return list;
    }
}
