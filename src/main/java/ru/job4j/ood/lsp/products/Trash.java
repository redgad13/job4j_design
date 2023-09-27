package ru.job4j.ood.lsp.products;

import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {
    private List<Food> list = new ArrayList<>();

    public Trash() { }

    public Trash(List<Food> list) {
        this.list = list;
    }

    @Override
    public void execute(List<Food> foods) {
        for (Food food : foods) {
            if (food.getTillExpiry() >= 0.75) {
                list.add(food);
            }
        }
    }
}
