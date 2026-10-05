package com.makerspace.wardwatch.storage;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Lets Gson save and load a family of classes (an abstract superclass and its
 * subclasses). When Gson reads JSON back into a superclass variable, it can't know
 * which subclass it was, so this factory writes an extra "type"
 * field with the subclass's name, and uses it to rebuild the right subclass.
 * (Given: you only need to change the register(...) calls in Storage.)
 *
 * @param <B> the superclass, e.g. Measurement
 */
public class SubtypeAdapterFactory<B> implements TypeAdapterFactory {
    private final Class<B> base;
    private final Map<String, Class<? extends B>> subtypes = new LinkedHashMap<>();

    public SubtypeAdapterFactory(Class<B> base) {
        this.base = base;
    }

    /** Adds a subclass, saved with "type": name. */
    public SubtypeAdapterFactory<B> register(String name, Class<? extends B> subtype) {
        subtypes.put(name, subtype);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!base.isAssignableFrom(type.getRawType())) {
            return null;                                       // not one of ours: Gson carries on as normal
        }
        TypeAdapter<JsonElement> json = gson.getAdapter(JsonElement.class);
        Map<String, TypeAdapter<?>> delegates = new LinkedHashMap<>();
        for (Map.Entry<String, Class<? extends B>> e : subtypes.entrySet()) {
            delegates.put(e.getKey(), gson.getDelegateAdapter(this, TypeToken.get(e.getValue())));
        }
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                String name = nameOf(value.getClass());
                JsonObject fields = ((TypeAdapter<T>) delegates.get(name)).toJsonTree(value).getAsJsonObject();
                JsonObject withType = new JsonObject();
                withType.addProperty("type", name);
                fields.entrySet().forEach(f -> withType.add(f.getKey(), f.getValue()));
                json.write(out, withType);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonObject object = json.read(in).getAsJsonObject();
                JsonElement name = object.remove("type");
                if (name == null || !delegates.containsKey(name.getAsString())) {
                    throw new JsonParseException("Missing or unknown \"type\": " + name);
                }
                return (T) delegates.get(name.getAsString()).fromJsonTree(object);
            }
        }.nullSafe();
    }

    private String nameOf(Class<?> cls) {
        for (Map.Entry<String, Class<? extends B>> e : subtypes.entrySet()) {
            if (e.getValue() == cls) {
                return e.getKey();
            }
        }
        throw new JsonParseException("Register " + cls.getSimpleName() + " with the SubtypeAdapterFactory");
    }
}
