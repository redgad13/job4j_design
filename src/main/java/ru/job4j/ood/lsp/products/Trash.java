package ru.job4j.ood.lsp.products;

public class Trash extends AbstractStore {
    Food food;

    public Trash(Food food) {
        this.food = food;
    }

    @Override
    public String execute() {
        return food.getName() + " eliminating";
    }
}
