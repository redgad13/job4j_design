package ru.job4j.ood.lsp.products;

import java.util.List;

public class Shop extends AbstractStore {

    @Override
    public List<Food> execute(List<Food> foods) {
        for (Food food : foods) {
            if (food.getTillExpiry() >= twentyFive && food.getTillExpiry() < seventyFive) {
                list.add(food);
            } else if (food.getTillExpiry() >= seventyFive && food.getTillExpiry() <= ninetyNine) {
                food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount());
                list.add(food);
            }
        }
        return list;
    }
}
