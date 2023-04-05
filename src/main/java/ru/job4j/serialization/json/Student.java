package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    private String name;
    private boolean isNerd;
    private int course;
    private Praepostor praepostor;
    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private String[] subjects;

    public Student() {
    }

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

    public String[] getSubjects() {
        return subjects;
    }
}
