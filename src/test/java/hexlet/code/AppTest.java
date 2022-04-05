package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {

    @Test
    public void stringTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();

        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        assertThat(schema.isValid("what does the fox say")).isFalse();

        schema.minLength(2);
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
    }

    @Test
    public void numberTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        schema.isValid(null); // true
        schema.required();

        assertThat(schema.isValid(null)).isFalse(); // false

        final int ten = 10;
        final int five = 5;
        final int four = 4;
        final int eleven = 11;
        assertThat(schema.isValid(ten)).isTrue(); // true
        assertThat(schema.isValid("5")).isFalse(); // false

        assertThat(schema.positive().isValid(ten)).isTrue(); // true
        assertThat(schema.isValid(-ten)).isFalse(); // false

        schema.range(five, ten);

        assertThat(schema.isValid(five)).isTrue(); // true
        assertThat(schema.isValid(ten)).isTrue(); // true
        assertThat(schema.isValid(four)).isFalse(); // false
        assertThat(schema.isValid(eleven)).isFalse(); // false
    }

    @Test
    public void mapTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue(); // true

        schema.required();

        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid(new HashMap())).isTrue(); // true
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue(); // true

        schema.sizeof(2);

        assertThat(schema.isValid(data)).isFalse();  // false
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue(); // true
    }

    @Test
    public void nestedTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        final int five = 5;
        final int hundred = 5;

        // shape - позволяет описывать валидацию для значений объекта Map по ключам.
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", hundred);
        assertThat(schema.isValid(human1)).isTrue(); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isTrue(); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isFalse(); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -five);
        assertThat(schema.isValid(human4)).isFalse(); // false
    }

}
