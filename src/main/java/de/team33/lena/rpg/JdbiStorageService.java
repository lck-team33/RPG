package de.team33.lena.rpg;

import de.team33.lena.rpg.model.RpgCharacter;
import org.jdbi.v3.core.Handle;

import java.util.*;

public class JdbiStorageService implements StorageService {

    @Override
    public String insertCharacter(final RpgCharacter character) {
        Database.JDBI.useTransaction(handle -> insertCharacter(handle, character.getId(), character.getProperties()));
        return character.getId();
    }

    private void insertCharacter(Handle handle, String id, Map<String, String> properties) {
        insertCharacterAnchor(handle, id);
        for(Map.Entry<String, String> entry : properties.entrySet()){
            insertProperty(handle, id, entry);
        }
    }

    private void insertCharacterAnchor(Handle handle, String id) {
        handle.createUpdate("INSERT INTO characters (id, ts_creation) VALUES(:id, :ts)")
                .bind("id", id)
                .bind("ts", System.currentTimeMillis())
                .execute();
    }

    private void insertProperty(Handle handle, String id, Map.Entry<String, String> entry) {
        handle.createUpdate("INSERT INTO character_properties (character_id, property, content, ts_creation)" +
                " VALUES(:id, :property, :content, :ts)")
                .bind("id", id)
                .bind("property", entry.getKey())
                .bind("content", entry.getValue())
                .bind("ts", System.currentTimeMillis())
                .execute();
    }

    @Override
    public List<RpgCharacter> getCharacters(String key, String value) {
        return Database.JDBI.withHandle(handle -> getCharactersByProperty(handle, key, value)); //besserer Name? D:
    }

    private List<RpgCharacter> getCharactersByProperty(Handle handle, String key, String value) {
        List<RpgCharacter> characterList = new ArrayList<>();
        handle.createQuery("SELECT character_id FROM character_properties WHERE property = :key AND content = :value")
                .bind("key", key)
                .bind("value", value)
                .mapTo(String.class)
                .stream()
                .distinct()
                .forEach(id -> characterList.add(getCharacterByID(handle, id)));
        return characterList;
    }

    private RpgCharacter getCharacterByID(Handle handle, String id){

        Map<String, String> properties = new TreeMap<>();

        handle.createQuery("SELECT property, content FROM character_properties WHERE character_id = :id")
                .bind("id", id)
                .mapToMap(String.class)
                .stream()
                .forEach(propertyPair -> properties.put(propertyPair.get("property"), propertyPair.get("content")));

        return new RpgCharacter(properties, id);
    }
}
