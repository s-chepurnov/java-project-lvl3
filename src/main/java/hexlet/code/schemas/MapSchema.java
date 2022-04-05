package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    private boolean shape = false;
    private Map<String, BaseSchema> schemas;

    private int sizeof = -1;

    @Override
    public boolean isValid(Object input) {
        if (!super.isValid(input)) {
            return false;
        }

        Map<String, Object> map = (Map<String, Object>) input;

        if (sizeof >= 0) {
            if (map == null) {
                return false;
            } else if (map.size() != sizeof) {
                return false;
            }
        }

        if (shape) {

            for (Map.Entry<String, BaseSchema> entry : this.schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema baseSchema = entry.getValue();
                Object value = map.get(key);

                if (!baseSchema.isValid(value)) {
                    return false;
                }

            }
        }

        return true;
    }

    public MapSchema required() {
        super.setRequired(true);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> mapSchemas) {
        this.shape = true;
        this.schemas = mapSchemas;
        return this;
    }

    public void sizeof(int size) {
        this.sizeof = size;
    }
}
