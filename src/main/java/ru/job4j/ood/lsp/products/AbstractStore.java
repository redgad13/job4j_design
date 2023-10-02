package ru.job4j.ood.lsp.products;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected List<Food> list = new ArrayList<>();

    public abstract void execute(List<Food> foods);

    public List<Food> getFood() {
        return list;
    }

}
