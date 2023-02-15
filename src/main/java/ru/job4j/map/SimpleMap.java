package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int hashCode = key.hashCode();
        int hash = hash(hashCode);
        int index = indexFor(hash);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
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
        capacity = capacity * 2;
    }

    @Override
    public V get(K key) {
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry.key.equals(key)) {
                return kvMapEntry.value;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry.key.equals(key)) {
                kvMapEntry = null;
                break;
            }
        }
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int point;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                boolean rsl = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (point < capacity) {
                    rsl = true;
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (Objects.equals(table[point].key, null)) {
                    point++;
                    hasNext();
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