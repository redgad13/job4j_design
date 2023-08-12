package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void whenIntegerMaxIsOK() {
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(11);
        list.add(-8);
        assertThat(maxMin.max(list, comparator)).isEqualTo(11);
    }

    @Test
    void whenStringMaxIsOK() {
        MaxMin maxMin = new MaxMin();
        Comparator<String> comparator = String::compareTo;
        List<String> list = new ArrayList<>();
        list.add("Anna");
        list.add("Bella");
        list.add("Donna");
        list.add("Zina");
        assertThat(maxMin.max(list, comparator)).isEqualTo("Zina");
    }

    @Test
    void whenIntegerMinIsOK() {
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(11);
        list.add(-8);
        assertThat(maxMin.min(list, comparator)).isEqualTo(-8);
    }

    @Test
    void whenStringMinIsOK() {
        MaxMin maxMin = new MaxMin();
        Comparator<String> comparator = String::compareTo;
        List<String> list = new ArrayList<>();
        list.add("Anna");
        list.add("Bella");
        list.add("Donna");
        list.add("Zina");
        assertThat(maxMin.min(list, comparator)).isEqualTo("Anna");
    }

    @Test
    void whenIntegerOneDigit() {
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        assertThat(maxMin.max(list, comparator)).isEqualTo(1);
    }

    @Test
    void whenStringOneWord() {
        MaxMin maxMin = new MaxMin();
        Comparator<String> comparator = String::compareTo;
        List<String> list = new ArrayList<>();
        list.add("Anna");
        assertThat(maxMin.min(list, comparator)).isEqualTo("Anna");
    }
}