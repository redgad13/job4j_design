package ru.job4j.ood.lsp.parking;

public interface Cell {
    boolean isAvailable();

    int getSize();

    void setAvailability(boolean available);
}
