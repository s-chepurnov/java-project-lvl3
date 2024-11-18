package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addCheck("required", val -> val != null && val.length() > 0);
        return this;
    }

    public StringSchema contains(String sequence) {
        addCheck("contains", val -> val.contains(sequence));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCheck("minLength", val -> val.length() >= minLength);
        return this;
    }

}
