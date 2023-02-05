package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.previousIndex() == index) {
                listIterator.add(value);
                break;
            }
            listIterator.next();
        }
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
        ListIterator<T> listIterator = list.listIterator();
        ListIterator<T> elIterator = elements.listIterator();
        T a = listIterator.next();
        T b = elIterator.next();
        while (listIterator.hasNext()) {
            if (a == b) {
                listIterator.remove();
                if (elIterator.hasNext()) {
                    b = elIterator.next();
                }
            }
            a = listIterator.next();
        }
    }
}