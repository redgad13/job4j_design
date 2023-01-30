package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        int inCount = in.size();
        int outCount = out.size();
        if (outCount == 0) {
            while (inCount != 0) {
                out.push(in.pop());
                outCount++;
                inCount--;
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}
