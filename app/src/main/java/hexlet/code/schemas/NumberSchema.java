package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck("required", Number.class::isInstance);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", val -> val == null || val > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        addCheck("range", val -> (int) val >= from && (int) val <= to);
        return this;
    }

}
