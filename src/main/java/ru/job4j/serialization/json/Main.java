package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        Student student = new Student("Vadim", false, 5,
                new Praepostor("Ivan", "Petrov", true), new String[]{"Math", "English"});
        String studentJson = gson.toJson(student);
        String studentInJSFormat = "{"
                + "\"name\":" + "\"" + student.getName() + "\","
                + "\"isNerd\":" + student.isNerd() + ","
                + "\"course\":" + student.getCourse() + ","
                + "\"praepostor\":"
                + "{"
                + "\"name\":" + "\"" + student.getPraepostor().getName() + "\","
                + "\"surname\":" + "\"" + student.getPraepostor().getSurname() + "\","
                + "\"isNerd\":" + student.getPraepostor().isNerd()
                + "}" + ","
                + "\"subjects\":" + Arrays.toString(student.getSubjects()) + "}";

        final Student gsonStudent = gson.fromJson(studentInJSFormat, Student.class);
        System.out.println(studentJson);
        System.out.println(gsonStudent);
    }
}
