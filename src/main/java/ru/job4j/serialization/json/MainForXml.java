package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class MainForXml {
    public static void main(String[] args) throws JAXBException {
        Student student = new Student("Sergei", false, 3,
                new Praepostor("Ivan", "Petrov", true),
                new String[]{"Math", "Russian", "Slovenian"});
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(student, writer);
            String rsl = writer.getBuffer().toString();
            System.out.println(rsl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
