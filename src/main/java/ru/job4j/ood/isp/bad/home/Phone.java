package ru.job4j.ood.isp.bad.home;

public class Phone implements Device {
    @Override
    public void connectToNet() {
        System.out.println("Connecting");
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
        System.out.println("Making phone calls");
    }
}
