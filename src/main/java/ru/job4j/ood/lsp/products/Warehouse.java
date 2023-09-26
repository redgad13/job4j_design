package ru.job4j.ood.lsp.products;

public class Warehouse extends AbstractStore {
    Food food;

    public Warehouse(Food food) {
        this.food = food;
    }

    @Override
    public String execute() {
        return food.getName() + " in storage";
    }
}
