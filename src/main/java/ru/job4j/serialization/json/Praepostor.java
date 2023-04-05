package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "praepostor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Praepostor {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String surname;
    private boolean isNerd;

    public Praepostor() {
    }

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
