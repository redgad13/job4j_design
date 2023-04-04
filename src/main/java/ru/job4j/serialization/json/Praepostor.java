package ru.job4j.serialization.json;

public class Praepostor {
    private String name;
    private String surname;
    private boolean isNerd;

    public Praepostor(String name, String surname, boolean isNerd) {
        this.name = name;
        this.surname = surname;
        this.isNerd = isNerd;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isNerd() {
        return isNerd;
    }
}
