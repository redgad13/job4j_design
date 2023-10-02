package ru.job4j.ood.lsp.products;

import java.util.List;

public class Shop extends AbstractStore {

    public Shop() {
    }

    public Shop(List<Food> list) {
        this.list = list;
    }

      @Override
    public void execute(List<Food> foods) {
        for (Food food : foods) {
            if (food.getTillExpiry() >= 0.25 && food.getTillExpiry() < 0.75) {
                list.add(food);
            }
        }
    }
}
