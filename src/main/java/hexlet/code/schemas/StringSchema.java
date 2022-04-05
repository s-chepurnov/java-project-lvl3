package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private String substr = "";
    private int minLength = -1;

    @Override
    public boolean isValid(Object input) {
        if (!super.isValid(input)) {
            return false;
        }

        String str = (String) input;
        boolean isEmpty = (str == null || str.isEmpty());

        if (!substr.isEmpty()) {
            if (!isEmpty && !str.contains(substr)) {
                return false;
            }
        }

        if (minLength >= 0) {
            if (str != null && str.length() < minLength) {
                return false;
            }
        }

        return true;
    }

    public StringSchema contains(String str) {
        substr = str;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema required() {
        super.setRequired(true);
        return this;
    }

    public String getSubstr() {
        return substr;
    }

    public int getMinLength() {
        return minLength;
    }
}
