import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }
    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> array = simpleConvert.toList("one", "two", "three", "four", "five");
        assertThat(array).hasSize(5)
                .containsSequence("four", "five")
                .containsExactly("one", "two", "three", "four", "five")
                .doesNotContain("seven")
                .element(0).isEqualTo("one");
    }

    @Test
    void checkToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> array = simpleConvert.toSet("one", "two", "three", "four", "five", "one");
        assertThat(array).hasSize(5)
                .doesNotContain("seven")
                .contains("one", "two", "three", "four", "five")
                .containsOnlyOnce("one")
                .hasOnlyElementsOfType(String.class);
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> stringMap = simpleConvert.toMap("one", "two", "three", "four", "five", "six");
        assertThat(stringMap).hasSize(6)
                .containsEntry("one", 0)
                .containsKey("five")
                .doesNotContainKeys("eight", "nine")
                .doesNotContainValue(7)
                .doesNotHaveToString("ones");
    }
}