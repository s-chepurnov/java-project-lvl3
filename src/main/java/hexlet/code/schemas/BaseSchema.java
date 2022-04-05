package hexlet.code.schemas;

public class BaseSchema {
    private boolean required = false;

    /**
     * Check the input - does it valid or not.
     * @param obj is an input to check
     * @return isValid
     */
    public boolean isValid(Object obj) {
        boolean isEmpty = false;

        if (obj == null) {
            isEmpty = true;
        } else if (obj.getClass() == String.class && ((String) obj).isEmpty()) {
            isEmpty = true;
        }

        if (required && isEmpty) {
            return false;
        }

        return true;
    }

    public final void setRequired(boolean isRequired) {
        this.required = isRequired;
    }

    public final boolean isRequired() {
        return required;
    }

}
