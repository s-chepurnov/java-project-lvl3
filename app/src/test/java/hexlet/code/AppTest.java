package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AppTest {

    @Test
    public void testStringValidator() {
        var v = new Validator();
        var schema = v.string();

        assertThat(schema.isValid("")).isTrue();

        schema.required();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();

        schema.minLength(toInt("7"));
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isFalse();

        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        var schema1 = v.string().required().minLength(toInt("10")).minLength(toInt("4"));
        assertThat(schema1.isValid("hexlet")).isTrue();
    }

    @Test
    public void testNumberValidator() {
        var v = new Validator();
        var schema = v.number();

        assertThat(schema.isValid(toInt("5"))).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.positive();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(toInt("-5"))).isFalse();
        assertThat(schema.isValid(toInt("0"))).isFalse();
        assertThat(schema.isValid(toInt("10"))).isTrue();

        schema.range(toInt("5"), toInt("10"));
        assertThat(schema.isValid(toInt("5"))).isTrue();
        assertThat(schema.isValid(toInt("10"))).isTrue();
        assertThat(schema.isValid(toInt("4"))).isFalse();
        assertThat(schema.isValid(toInt("11"))).isFalse();

        schema.range(toInt("6"), toInt("9"));
        assertThat(schema.isValid(toInt("5"))).isFalse();
        assertThat(schema.isValid(toInt("5"))).isFalse();
    }

    @Test
    public void testMapValidator() {
        var v = new Validator();
        var schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.sizeof(toInt("2"));
        assertThat(schema.isValid(new HashMap<>())).isFalse();
        Map<String, String> actual1 = new HashMap<>();
        actual1.put("key1", "value1");
        assertThat(schema.isValid(actual1)).isFalse();
        actual1.put("key2", "value2");
        assertThat(schema.isValid(actual1)).isTrue();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required().contains("ya"));
        schemas.put("lastName", v.string().required().contains("ov"));
        schema.shape(schemas);

        Map<String, String> actual2 = new HashMap<>();
        actual2.put("firstName", "Kolya");
        actual2.put("lastName", "Ivanov");
        assertThat(schema.isValid(actual2)).isTrue();

        Map<String, String> actual3 = new HashMap<>();
        actual3.put("firstName", "Maya");
        actual3.put("lastName", "Krasnova");
        assertThat(schema.isValid(actual3)).isTrue();

        Map<String, String> actual4 = new HashMap<>();
        actual4.put("firstName", "John");
        actual4.put("age", "B");
        assertThat(schema.isValid(actual4)).isFalse();
    }

    //to avoid linter errors "magic numbers"
    public int toInt(String val) {
        return Integer.parseInt(val);
    }

}
