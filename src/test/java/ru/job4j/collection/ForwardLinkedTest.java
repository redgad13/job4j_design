package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

class ForwardLinkedTest {

    private ForwardLinked<Integer> list;
    private ForwardLinked<Integer> linked;

    @BeforeEach
    public void initData() {
        list = new ForwardLinked<>();
        list.add(1);
        list.add(2);
    }

    @Test
    void checkIteratorSimple() {
        assertThat(list).hasSize(2);
        list.add(3);
        list.add(4);
        assertThat(list).hasSize(4);
    }

    @Test
    void checkAdd() {
        assertThat(list).containsExactly(1, 2);
        list.add(3);
        assertThat(list).containsExactly(1, 2, 3);
    }

    @Test
    void whenAddIterHasNextTrue() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenHasIteratorAndAddThenHasNextExceptionThrown() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        list.add(3);
        assertThatThrownBy(it::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenHasIteratorAndAddThenNextExceptionThrown() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        list.add(3);
        assertThatThrownBy(it::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenAddIterNextOne() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    void whenEmptyIterHashNextFalse() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isFalse();
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    void whenAddIterMultiHasNextTrue() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOneNextTwo() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    void whenGetIteratorTwiceThenEveryFromBegin() {
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(1);
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(2);
        assertThat(first.hasNext()).isFalse();
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(1);
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(2);
        assertThat(second.hasNext()).isFalse();
    }

    @BeforeEach
    void init() {
        linked = new ForwardLinked<>();
    }

    @Test
    void whenSize0ThenReturnFalse() {
        assertThat(linked.revert()).isFalse();
    }

    @Test
    void whenSize1ThenReturnFalse() {
        linked.add(1);
        assertThat(linked.revert()).isFalse();
    }

    @Test
    void whenAddAndRevertTrue() {
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        assertThat(linked).containsSequence(1, 2, 3, 4);
        assertThat(linked.revert()).isTrue();
        assertThat(linked).containsSequence(4, 3, 2, 1);
    }
}