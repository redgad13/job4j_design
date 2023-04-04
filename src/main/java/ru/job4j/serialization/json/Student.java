package ru.job4j.serialization.json;

public class Student {
    private String name;
    private boolean isNerd;
    private int course;
    private Praepostor praepostor;
    private String[] subjects;

    public Student(String name, boolean isNerd, int course, Praepostor praepostor, String[] subjects) {
        this.name = name;
        this.isNerd = isNerd;
        this.course = course;
        this.praepostor = praepostor;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public boolean isNerd() {
        return isNerd;
    }

    public int getCourse() {
        return course;
    }

    public Praepostor getPraepostor() {
        return praepostor;
    }
}
