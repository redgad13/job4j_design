package ru.job4j.serialization.json;

import org.json.JSONObject;

public class MainForJSON {
    public static void main(String[] args) {
        Praepostor praepostor = new Praepostor("Ivan", "Petrov", true);
        Student student = new Student("Vadim", false, 5,
                praepostor, new String[]{"Math", "English"});
        JSONObject jsonStudent = new JSONObject();
        JSONObject jsonPraepostor = new JSONObject(praepostor);
        jsonStudent.put("name", student.getName());
        jsonStudent.put("nerd", student.isNerd());
        jsonStudent.put("course", student.getCourse());
        jsonStudent.put("praepostor", student.getPraepostor());
        jsonStudent.put("subjects", student.getSubjects());
        System.out.println(jsonPraepostor);
        System.out.println(jsonStudent);
    }
}
