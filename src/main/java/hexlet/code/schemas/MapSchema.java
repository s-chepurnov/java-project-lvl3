package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addCheck("required", Map.class::isInstance);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", val -> val.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck(
                "shape",
                map -> {
                    return schemas.entrySet().stream().allMatch(e -> {
                        var v = map.get(e.getKey());
                        var schema = e.getValue();
                        return schema.isValid((T) v);
                    });
                }
        );
        return this;
    }


}
