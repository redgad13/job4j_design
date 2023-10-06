package ru.job4j.ood.lsp.parking;

public class PassangerCell implements Cell {
    final int size = 1;
    boolean available = true;

    @Override
    public boolean isAvailable() {
        return true;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void setAvailability(boolean available) {
        this.available = available;
    }
}
