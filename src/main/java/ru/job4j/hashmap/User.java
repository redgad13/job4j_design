package ru.job4j.hashmap;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthDay = new GregorianCalendar(1988, Calendar.APRIL, 13);
        User user1 = new User("Vadim", 1, birthDay);
        User user2 = new User("Vadim", 1, birthDay);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        int firstHash = user1.hashCode();
        int secondHash = user2.hashCode();
        int hash1 = firstHash ^ (firstHash >>> 16);
        int hash2 = secondHash ^ (secondHash >>> 16);
        int bucket1 = hash1 & 15;
        int bucket2 = hash2 & 15;
        System.out.println("HashCodes of Users are: " + firstHash + " " + secondHash);
        System.out.println("Hashes in MAP are: " + hash1 + " " + hash2);
        System.out.println("Buckets in MAP are: " + bucket1 + " " + bucket2);
    }
}
