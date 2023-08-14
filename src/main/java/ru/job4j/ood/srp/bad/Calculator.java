package ru.job4j.ood.srp.bad;

public class Calculator implements Math {
    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Override
    public void printResult(int rsl) {
        System.out.println(rsl);
    }
}
