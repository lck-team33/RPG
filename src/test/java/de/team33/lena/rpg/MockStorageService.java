package de.team33.lena.rpg;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class MockStorageService implements StorageService {

    private final Map<String, Map<String, String>> backing = new TreeMap<>();

    @Override
    public String insertCharacter(final Map<String, String> properties) {
        final String id = UUID.randomUUID().toString();
        backing.put(id, new TreeMap<>(properties));
        return id;
    }
}
