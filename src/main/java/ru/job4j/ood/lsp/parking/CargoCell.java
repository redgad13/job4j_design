package ru.job4j.ood.lsp.parking;

public class CargoCell implements Cell {
    int size;

    public CargoCell(PassangerCell passangerCell, int factor) {
        this.size = passangerCell.getSize() * factor;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    public int getSize() {
        return size;
    }
}
