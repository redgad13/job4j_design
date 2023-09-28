package ru.job4j.ood.lsp.products;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected List<Food> list = new ArrayList<>();

    protected void execute(List<Food> foods) {
    }
}
