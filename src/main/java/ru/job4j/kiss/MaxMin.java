package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return minOrMax(value, comparator, false);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
       return minOrMax(value, comparator, true);
    }

    private <T> T minOrMax(List<T> value, Comparator<T> comparator, boolean isMin) {
        T rsl = value.get(0);
        for (int i = 0; i < value.size() - 1; i++) {
            T next = value.get(i + 1);
            if (isMin) {
                rsl = comparator.compare(rsl, next) < 0 ? rsl : next;
            } else {
                rsl = comparator.compare(rsl, next) > 0 ? rsl : next;
            }
        }
        return rsl;
    }
}