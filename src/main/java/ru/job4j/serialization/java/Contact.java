package ru.job4j.serialization.java;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;
    private static final Logger LOG = LoggerFactory.getLogger(Contact.class.getName());


    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone
                + '\'' + "}";
    }

    public static void main(String[] args) throws IOException {
        final Contact contact = new Contact(123456, "464651");
        System.out.println(contact);
        File tempFile = Files.createTempFile("serialization.txt", null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact deserContact = (Contact) ois.readObject();
            System.out.println(deserContact);
        } catch (ClassNotFoundException e) {
            LOG.error("no such file", e);
        }
    }
}
