package de.team33.lena.rpg;

import de.team33.lena.rpg.model.RpgCharacter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MockStorageService implements StorageService {

    private final Map<String, RpgCharacter> backing = new TreeMap<>();

    @Override
    public String insertCharacter(RpgCharacter character) {
        backing.put(character.getId(), character);
        return null;
    }

    @Override
    public List<RpgCharacter> getCharacters(String key, String value) {
        return null;
    }
}
