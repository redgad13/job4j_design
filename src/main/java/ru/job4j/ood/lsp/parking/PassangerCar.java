package ru.job4j.ood.lsp.parking;

public class PassangerCar implements Car {
    static int size = 1;

    @Override
    public boolean takeTheCell(Cell cell) {
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }
}
