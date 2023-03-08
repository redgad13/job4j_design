package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Vadim Kuzin");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("city")).isEqualTo("Saint-Petersburg");
    }

    @Test
    void whenOnlyKey() {
        String path = "./data/only_keys.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("key1")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValueHasEqualsSymbol() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("this")).isEqualTo("is for test =?");
    }

    @Test
    void whenOnlyValue() {
        String path = "./data/only_value.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoEqualsSign() {
        String path = "./data/no_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("")).isInstanceOf(IllegalArgumentException.class);
    }
}