package ru.job4j.ood.srp.bad;

import java.util.List;

public class StringOperation implements DataOperation {
    List<String> list;

    @Override
    public void add(String data) {
        list.add(data);
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }
}
