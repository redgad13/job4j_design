package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity) {
            expand();
        }
        if (table[count] == null) {
            rsl = true;
            table[count] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if (count >= capacity * LOAD_FACTOR) {
            capacity = capacity * 2;
        }
        modCount++;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        for (MapEntry<K, V> entry : table) {
            if (key.equals(entry.key)) {
                rsl = entry.value;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        for (MapEntry<K, V> entry : table) {
            if (key.equals(entry.key)) {
                entry.key = null;
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int point;

            @Override
            public boolean hasNext() {
                while (point < capacity && Objects.equals(null, table[point])) {
                    point++;
                }
                return point < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}