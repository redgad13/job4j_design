package ru.job4j.ood.lsp.products;

public abstract class AbstractStore {
    Food food;

    public String execute() {
        return food.getName();
    }
}
