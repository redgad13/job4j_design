package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    public int getTotalSize(List<Cell> cells) {
        int rsl = 0;
        for (Cell cell : cells) {
            rsl += cell.getSize();
        }
        return rsl;
    }

    public boolean getNewPassCar(List<Cell> cells, Car car) {
        boolean rsl = false;
        for (Cell passCell : getPassengerCells(cells)) {
            if (passCell.isAvailable()) {
                passCell.setAvailability(false);
                car.takeTheCell(passCell);
                rsl = true;
            }
        }
        return rsl;
    }

    public boolean getNewCargoCar(List<Cell> cells, Car car) {
        boolean rsl = false;
        int factor = car.getSize();
        for (Cell cargoCell : getCargoCells(cells)) {
            if (cargoCell.isAvailable()) {
                cargoCell.setAvailability(false);
                car.takeTheCell(cargoCell);
                rsl = true;
            }
        }
        List<Cell> passCells = getPassengerCells(cells);
        if (passCells.size() >= factor) {
            for (Cell passCell : passCells) {
                if (passCell.isAvailable() && factor > 0) {
                    passCell.setAvailability(false);
                    car.takeTheCell(passCell);
                    factor--;
                }
                if (factor == 0) {
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    private List<Cell> getPassengerCells(List<Cell> cells) {
        List<Cell> rsl = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.getSize() == 1) {
                rsl.add(cell);
            }
        }
        return rsl;
    }

    private List<Cell> getCargoCells(List<Cell> cells) {
        List<Cell> rsl = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.getSize() > 1) {
                rsl.add(cell);
            }
        }
        return rsl;
    }
}
