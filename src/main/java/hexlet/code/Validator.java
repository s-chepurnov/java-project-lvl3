package hexlet.code;

import hexlet.code.schemas.StringSchema;

public final class Validator {

    public StringSchema string() {
        return new StringSchema();
    }

    public boolean isValid(Object obj) {
        return false;
    }

}
