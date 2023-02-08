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
        input = new ArrayList<>(Arrays.asList(1, 3, 3, 4, 5));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(6).containsSequence(1, 2, 3, 3, 4, 5);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 5, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(6).containsSequence(1, 2, 3, 3, 4, 5);
    }

    @Test
    void whenEquals3Remove() {
        Predicate<Integer> predicate = i -> i == 3;
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(3).containsExactly(1, 4, 5);
    }

    @Test
    void whenEquals3Replace() {
        Predicate<Integer> predicate = i -> i == 3;
        ListUtils.replaceIf(input, predicate, 5);
        assertThat(input).hasSize(5).containsExactly(1, 5, 5, 4, 5);
    }

    @Test
        void whenRemoveAll() {
        ArrayList<Integer> newList = new ArrayList<>(List.of(1, 3));
        ListUtils.removeAll(input, newList);
        assertThat(input).hasSize(2).containsExactly(4, 5);
    }
}