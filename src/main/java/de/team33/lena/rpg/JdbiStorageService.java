package de.team33.lena.rpg;

import de.team33.lena.rpg.model.RpgCharacter;
import org.jdbi.v3.core.Handle;

import java.util.Map;

public class JdbiStorageService implements StorageService {

    @Override
    public String insertCharacter(final RpgCharacter character) {
        Database.JDBI.inTransaction(handle -> insertCharacter(handle, character.getId(), character.getProperties()));
        return character.getId();
    }

    private Object insertCharacter(Handle handle, String id, Map<String, String> properties) {
        insertCharacterAnchor(handle, id);
        for(Map.Entry<String, String> entry : properties.entrySet()){
            insertProperty(handle, id, entry);
        }
        return null; // why? D:
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
}
