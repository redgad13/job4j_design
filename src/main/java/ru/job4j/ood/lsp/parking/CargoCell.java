package ru.job4j.ood.lsp.parking;

public class CargoCell implements Cell {
    int size;
    boolean available = true;

    public CargoCell(Cell passangerCell, int factor) {
        this.size = passangerCell.getSize() * factor;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void setAvailability(boolean available) {
        this.available = available;
    }
}
