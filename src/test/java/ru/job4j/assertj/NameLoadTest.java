package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenPassedArrayIsEmpty() {
        String[] names = new String[0];
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void whenParameterHasNoEqualsSign() {
        String parameter = "hello world";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(parameter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain the symbol \"=\"", parameter);
    }

    @Test
    void whenParameterStartsWithEqualsSign() {
        String parameter = "=world";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(parameter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a key", parameter);
    }

    @Test
    void whenParameterEndsWithEqualsSign() {
        String parameter = "world=";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(parameter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value", parameter);
    }
}