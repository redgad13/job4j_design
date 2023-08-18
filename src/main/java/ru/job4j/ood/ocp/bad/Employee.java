package ru.job4j.ood.ocp.bad;

import java.util.ArrayList;

public class Employee {
    private int salary;
    private String position;

    public int getSalary(Person person) {
        return salary;
    }

    public ArrayList<Employee> getSubordinates() {
        return new ArrayList<>();
    }
}
