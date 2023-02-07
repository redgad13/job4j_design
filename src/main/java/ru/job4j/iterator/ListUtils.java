package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.listIterator(index).add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.listIterator(index + 1).add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (filter.test(list.get(listIterator.nextIndex()))) {
                listIterator.next();
                listIterator.remove();
            }
            listIterator.next();
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (filter.test(list.get(listIterator.nextIndex()))) {
                listIterator.next();
                listIterator.set(value);
            }
            listIterator.next();
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        removeIf(list, el -> Objects.equals(el, elements.get(elements.listIterator().nextIndex())));
    }
}