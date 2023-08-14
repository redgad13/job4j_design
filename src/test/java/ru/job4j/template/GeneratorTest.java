package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {
    @Test
    public void whenProduceIsOk() {
    Generator generator = new GeneratorForName();
    Map<String, String> map = new HashMap<>();
    map.put("Petr Arsentev", "you");
    String name = generator.produce("I am a ${name}, Who are ${subject}? ", map);
    assertThat(generator.produce(name, map)).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

}