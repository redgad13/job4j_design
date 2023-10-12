package ru.job4j.ood.isp.bad.music;

public class Drums implements Instrument {
    @Override
    public void slide() {
    }

    @Override
    public void bend() {
    }

    @Override
    public void hit() {
        System.out.println("Hitting a string");
    }
}
