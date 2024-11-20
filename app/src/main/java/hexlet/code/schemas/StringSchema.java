package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addCheck("required", str -> str != null && !str.isEmpty());
        return this;
    }

    public StringSchema contains(String value) {
        addCheck("contains", str -> str.contains(value));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCheck("minLength", str -> str.length() >= minLength);
        return this;
    }

}
