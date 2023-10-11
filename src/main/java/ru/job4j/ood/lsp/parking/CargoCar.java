package ru.job4j.ood.lsp.parking;

public class CargoCar implements Car {
    static int size;

    public CargoCar(int passCarSize, int factor) {
        size = passCarSize * factor;
    }

    @Override
    public boolean takeTheCell(Cell cell) {
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }
}
