package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    private boolean positive = false;
    private boolean range = false;
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

    @Override
    public boolean isValid(Object number) {
        if (!super.isValid(number)) {
            return false;
        }

        boolean isEmpty = (number == null);
        Integer num = null;
        if (!isEmpty) {
            if (number.getClass() == Integer.class) {
                num = (Integer) number;
            } else {
                return false;
            }
        }

        if (positive) {
            if (!isEmpty && num <= 0) {
                return false;
            }
        }

        if (range) {
            if (isEmpty || (num < this.min || num > this.max)) {
                return false;
            }
        }

        return true;
    }

    public void range(int minValue, int maxValue) {
        range = true;
        this.min = minValue;
        this.max = maxValue;
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public boolean isRange() {
        return range;
    }
    public NumberSchema required() {
        super.setRequired(true);
        return this;
    }

}
