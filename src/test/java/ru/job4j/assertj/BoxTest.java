package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void whenSphereThen0Verticies() {
        Box box = new Box(0, 4);
        int result = 0;
        assertThat(result).isEqualTo(box.getNumberOfVertices());
    }

    @Test
    void whenCubeThen8Verticies() {
        Box box = new Box(8, 4);
        int result = 8;
        assertThat(result).isEqualTo(box.getNumberOfVertices());
    }

    @Test
    void whenExists() {
        Box box = new Box(4, 12);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDoesntExist() {
        Box box = new Box(1, 12);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void whenVertex0Edge3ThenAreaEquals113() {
        Box box = new Box(0, 3);
        double rsl = 4 * Math.PI * (3 * 3);
        assertThat(rsl).isCloseTo(box.getArea(), Percentage.withPercentage(0.1));
    }

    @Test
    void whenVertex4Edge5ThenAreaEquals51() {
        Box box = new Box(4, 5);
        double rsl = Math.sqrt(3) * (5 * 5);
        assertThat(rsl).isCloseTo(box.getArea(), withPrecision(0.05));
    }
}