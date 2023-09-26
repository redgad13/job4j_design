package ru.job4j.ood.lsp.products;

public class Shop extends AbstractStore {
    Food food;

    public Shop(Food food) {
        this.food = food;
    }

    @Override
    public String execute() {
        return food.getName() + " is selling";
    }
}
