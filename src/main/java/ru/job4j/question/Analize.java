package ru.job4j.question;

import jdk.jfr.consumer.RecordedStackTrace;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int added = 0;
        int deleted = 0;
        Map<Integer, String> previousMap = new HashMap<>();
        for (User prevUser : previous) {
            previousMap.put(prevUser.getId(), prevUser.getName());
        }
        Map<Integer, String> currMap = new HashMap<>();
        for (User currUser : current) {
            currMap.put(currUser.getId(), currUser.getName());
        }
        for (User user : current) {
            int id = user.getId();
            if (previousMap.containsKey(id)
                    && !previousMap.get(id).equals(user.getName())) {
                changed++;
            } else if (!previousMap.containsKey(id)) {
                added++;
            }
        }
        for (User prev : previous) {
            if (!currMap.containsKey(prev.getId())) {
                deleted++;
                break;
            }
        }
        return new Info(added, changed, deleted);
    }
}
