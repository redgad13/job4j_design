package ru.job4j.collection;

import java.util.Optional;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T rsl = in.pop();
        while (rsl != null) {
            out.push(rsl);
            rsl = in.pop();
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}