package ru.job4j.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {
    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("data/random.txt", "rw");
            randomAccess.write(100);
            randomAccess.writeChar('f');
            randomAccess.writeUTF("fdsaf");
            randomAccess.seek(0);
            randomAccess.seek(1);
            System.out.println(randomAccess.read());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.read());
        } catch (EOFException e) {
            System.out.println("End of file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
