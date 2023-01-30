package ru.job4j.collection;

<<<<<<< HEAD
=======
import java.util.Optional;

>>>>>>> origin/master
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
<<<<<<< HEAD
        int inCount = in.size();
        int outCount = out.size();
        if (outCount == 0) {
            while (inCount != 0) {
                out.push(in.pop());
                outCount++;
                inCount--;
            }
=======
        T rsl = in.pop();
        while (rsl != null) {
            out.push(rsl);
            rsl = in.pop();
>>>>>>> origin/master
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}