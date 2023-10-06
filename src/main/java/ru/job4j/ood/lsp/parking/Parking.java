package ru.job4j.ood.lsp.parking;

import java.util.List;

public class Parking {
    List<Cell> cells;

    public int getTotalSize(List<Cell> cells) {
        int rsl = 0;
        for (Cell cell : cells) {
            rsl += cell.getSize();
        }
        return rsl;
    }

    public boolean occupyCell(List<Cell> cells) {
       return true;
    }

    public boolean getNewCar(List<Cell> cells, Car car) {
          return true;
    }
}
