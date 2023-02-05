package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3, 4, 5));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(5).containsSequence(1, 2, 3, 4, 5);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 5, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(5).containsSequence(1, 2, 3, 4, 5);
    }

    @Test
    void removeIf3() {
        Predicate<Integer> predicate = i -> i == 3;
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(3).containsSequence(1, 4, 5);
    }

    @Test
    void replaceIf3() {
        Predicate<Integer> predicate = i -> i == 3;
        ListUtils.replaceIf(input, predicate, 9);
        assertThat(input).hasSize(4).containsSequence(1, 9, 4, 5);
    }

    @Test
    void removeAllIf3and4() {
        ArrayList<Integer> el = new ArrayList<>(Arrays.asList(3, 4));
        ListUtils.removeAll(input, el);
        assertThat(input).hasSize(2).containsSequence(1, 5);
    }
}