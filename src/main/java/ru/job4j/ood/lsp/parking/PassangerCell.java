package ru.job4j.ood.lsp.parking;

public class PassangerCell implements Cell {
    final int size = 1;

    @Override
    public boolean isAvailable() {
        return false;
    }

    public int getSize() {
        return size;
    }
}
