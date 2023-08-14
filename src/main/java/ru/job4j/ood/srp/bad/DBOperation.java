package ru.job4j.ood.srp.bad;


import java.util.List;
import java.util.Map;

public interface DBOperation {
    public void insert(Map<String, String> data);

    public List<java.util.Map<String, String>> get(String key);
}
