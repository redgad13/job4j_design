package ru.job4j.ood.dip;

import java.util.ArrayList;

public class Faculty {
    private ArrayList<Student> students;

    public Faculty(ArrayList<Student> students) {
        this.students = students;
    }

    public int getStudents() {
        return this.students.size();
    }
}
