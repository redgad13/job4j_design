package ru.job4j.ood.lsp.parking;

public class PassangerCar implements Car {

    @Override
    public boolean takeTheCell() {
        return false;
    }
}
