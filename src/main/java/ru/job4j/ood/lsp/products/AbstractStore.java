package ru.job4j.ood.lsp.products;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected List<Food> list = new ArrayList<>();
    protected double twentyFive = 0.25;
    protected double seventyFive = 0.75;
    protected double ninetyNine = 0.99;

    public abstract List<Food> execute(List<Food> foods);

    public List<Food> getFood() {
        return list;
    }

}
