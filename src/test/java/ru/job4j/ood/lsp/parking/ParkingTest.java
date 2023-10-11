package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParkingTest {

    @Test
    void getTotalSize() {
        List<Cell> cells = new ArrayList<>();
        Cell passCell1 = new PassangerCell();
        Cell passCell2 = new PassangerCell();
        Cell passCell3 = new PassangerCell();
        Cell cargoCell1 = new CargoCell(passCell1, 2);
        Cell cargoCell2 = new CargoCell(passCell1, 2);
        cells.add(passCell1);
        cells.add(passCell2);
        cells.add(passCell3);
        cells.add(cargoCell1);
        cells.add(cargoCell2);
        Parking parking = new Parking();
        int rsl = parking.getTotalSize(cells);
        assertThat(rsl).isEqualTo(7);
    }

    @Test
    void getNewPassengerCarIsOk() {
        List<Cell> cells = new ArrayList<>();
        Cell passCell1 = new PassangerCell();
        Cell passCell2 = new PassangerCell();
        Cell passCell3 = new PassangerCell();
        Cell cargoCell1 = new CargoCell(passCell1, 2);
        Cell cargoCell2 = new CargoCell(passCell1, 2);
        cells.add(passCell1);
        cells.add(passCell2);
        cells.add(passCell3);
        cells.add(cargoCell1);
        cells.add(cargoCell2);
        Parking parking = new Parking();
        Car passCar = new PassangerCar();
        boolean rsl = parking.getNewPassCar(cells, passCar);
        assertThat(rsl).isTrue();
    }

    @Test
    void getNewCargoCarIsOkWhenHaveCargoCell() {
        List<Cell> cells = new ArrayList<>();
        Cell passCell1 = new PassangerCell();
        Cell passCell2 = new PassangerCell();
        Cell passCell3 = new PassangerCell();
        Cell cargoCell1 = new CargoCell(passCell1, 2);
        Cell cargoCell2 = new CargoCell(passCell1, 2);
        cells.add(passCell1);
        cells.add(passCell2);
        cells.add(passCell3);
        cells.add(cargoCell1);
        cells.add(cargoCell2);
        Parking parking = new Parking();
        int passCarSize = new PassangerCar().getSize();
        Car cargoCar = new CargoCar(passCarSize, 2);
        boolean rsl = parking.getNewCargoCar(cells, cargoCar);
        assertThat(rsl).isTrue();
    }

    @Test
    void getNewCargoCarIsOkWhenHaveTwoPassCells() {
        List<Cell> cells = new ArrayList<>();
        Cell passCell1 = new PassangerCell();
        Cell passCell2 = new PassangerCell();
        Cell passCell3 = new PassangerCell();
        cells.add(passCell1);
        cells.add(passCell2);
        cells.add(passCell3);
        Parking parking = new Parking();
        int passCarSize = new PassangerCar().getSize();
        Car cargoCar = new CargoCar(passCarSize, 2);
        boolean rsl = parking.getNewCargoCar(cells, cargoCar);
        assertThat(rsl).isTrue();
    }

    @Test
    void getNewPassengerCarIsNotOkNoAvailableCells() {
        List<Cell> cells = new ArrayList<>();
        Cell passCell1 = new PassangerCell();
        Cell cargoCell1 = new CargoCell(passCell1, 2);
        Cell cargoCell2 = new CargoCell(passCell1, 2);
        cells.add(cargoCell1);
        cells.add(cargoCell2);
        Parking parking = new Parking();
        Car passCar = new PassangerCar();
        boolean rsl = parking.getNewPassCar(cells, passCar);
        assertThat(rsl).isFalse();
    }

    @Test
    void getNewCargoCarIsNotOkNoAvailableCells() {
        List<Cell> cells = new ArrayList<>();
        Cell passCell1 = new PassangerCell();
        cells.add(passCell1);
        Parking parking = new Parking();
        int passCarSize = new PassangerCar().getSize();
        Car cargoCar = new CargoCar(passCarSize, 2);
        boolean rsl = parking.getNewCargoCar(cells, cargoCar);
        assertThat(rsl).isFalse();
    }
}