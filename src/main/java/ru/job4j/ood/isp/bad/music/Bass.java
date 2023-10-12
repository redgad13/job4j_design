package ru.job4j.ood.isp.bad.music;

public class Bass implements Instrument {
    @Override
    public void slide() {
        System.out.println("Making a slide");
    }

    @Override
    public void bend() {
        System.out.println("Making a bend");
    }

    @Override
    public void hit() {
        System.out.println("Hitting a string");
    }
}
