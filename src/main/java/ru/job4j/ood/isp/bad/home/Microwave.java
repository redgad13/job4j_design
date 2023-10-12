package ru.job4j.ood.isp.bad.home;

public class Microwave implements Device {
    @Override
    public void connectToNet() {

    }

    @Override
    public void turnOn() {
        System.out.println("Turning on");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off");
    }

    @Override
    public void execute() {
        System.out.println("Warming products");
    }
}
