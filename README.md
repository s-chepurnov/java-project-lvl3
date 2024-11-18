### Hexlet tests, linter status:
[![Actions Status](https://github.com/s-chepurnov/java-project-lvl3/workflows/hexlet-check/badge.svg)](https://github.com/s-chepurnov/java-project-lvl3/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/a034274bd4c94009fdba/maintainability)](https://codeclimate.com/github/s-chepurnov/java-project-lvl3/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/a034274bd4c94009fdba/test_coverage)](https://codeclimate.com/github/s-chepurnov/java-project-lvl3/test_coverage)

Data validator for String, Integer, Map.

Integer:
```java
var v = new Validator();
var schema = v.number();

assertThat(schema.isValid(5)).isTrue();
assertThat(schema.isValid(null)).isTrue();

schema.positive();
assertThat(schema.isValid(null)).isTrue();
```

String:
```java
var v = new Validator();
var schema = v.string();

assertThat(schema.isValid("")).isTrue();

schema.required();
assertThat(schema.isValid("what does the fox say")).isTrue();
assertThat(schema.isValid("hexlet")).isTrue();
assertThat(schema.isValid("")).isFalse();
assertThat(schema.isValid(null)).isFalse();
```

Map:
```java
var v = new Validator();
var schema = v.map();

assertThat(schema.isValid(null)).isTrue();
assertThat(schema.isValid(new HashMap<>())).isTrue();

schema.required();
assertThat(schema.isValid(null)).isFalse();
assertThat(schema.isValid(new HashMap<>())).isTrue();

schema.sizeof(2);
assertThat(schema.isValid(new HashMap<>())).isFalse();
Map<String, String> actual1 = new HashMap<>();
actual1.put("key1", "value1");
assertThat(schema.isValid(actual1)).isFalse();
actual1.put("key2", "value2");
assertThat(schema.isValid(actual1)).isTrue();
```
