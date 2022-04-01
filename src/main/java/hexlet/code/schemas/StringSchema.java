package hexlet.code.schemas;

public final class StringSchema {
    private boolean required = false;
    private String substr = "";

    public boolean isValid(String str) {
        boolean isEmpty = (str == null || str.isEmpty());
        if (required) {
            if (isEmpty) {
                return false;
            }
        } else {
            if (isEmpty) {
                return true;
            }
        }

        return str.contains(substr);
    }

    public void required() {
        required = true;
    }

    public StringSchema contains(String str) {
        substr = str;
        return this;
    }

    public boolean isRequired() {
        return required;
    }

    public String getSubstr() {
        return substr;
    }

}
