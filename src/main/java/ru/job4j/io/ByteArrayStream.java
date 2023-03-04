package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class ByteArrayStream {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{'j', 'a', 'v', 'a'};
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();
        String str = "123456789";
        byte[] newBytes = str.getBytes();
        ByteArrayInputStream stream1 = new ByteArrayInputStream(newBytes, 3, 4);
        int data1;
        while ((data1 = stream1.read()) != -1) {
            System.out.print((char) data1);
        }
        System.out.println();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] bytes2 = "Message from ByteArrayOutputStream".getBytes();
        outStream.writeBytes(bytes2);
        System.out.println(outStream);

        byte[] newBytes2 = outStream.toByteArray();
        for (byte b : newBytes2) {
            System.out.print((char) b);
        }
        try (FileOutputStream outputStream = new FileOutputStream("data/message.txt")) {
            outStream.writeTo(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
