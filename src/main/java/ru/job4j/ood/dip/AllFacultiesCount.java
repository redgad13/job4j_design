package ru.job4j.ood.dip;

import java.util.ArrayList;

public class AllFacultiesCount {
    ArrayList<Faculty> faculties;

    public int getTotalStudents(ArrayList<Faculty> faculties) {
        int rsl = 0;
        for (Faculty faculty : faculties) {
            rsl += faculty.getStudents();
        }
        return rsl;
    }
}
