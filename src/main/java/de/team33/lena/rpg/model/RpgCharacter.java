package de.team33.lena.rpg.model;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class RpgCharacter {

    private String id;
    private Map<String, String> properties;

    public String getId() {
        return id;
    }

    public RpgCharacter setId(final String id) {
        this.id = id;
        return this;
    }

    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public RpgCharacter addProperty(final String key, final String value) {
        properties.put(key, value);
        return this;
    }

    public RpgCharacter setProperties(final Map<String, String> properties) {
        this.properties = new TreeMap<>(properties);
        return this;
    }
}
