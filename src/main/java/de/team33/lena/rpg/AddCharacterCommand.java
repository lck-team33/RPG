package de.team33.lena.rpg;

import org.jdbi.v3.core.Handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class AddCharacterCommand implements Runnable {

    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private final Map<String, String> properties = new LinkedHashMap<>();

    @Override
    public void run() {
        try {
            System.out.println("Du möchtest einen Charakter anlegen.");
            System.out.println("Im Folgenden werden Eigenschaften abgefragt.");
            System.out.println("Diese kannst du benennen (property) und ihnen einen Wert zuweisen (value).");
            System.out.println("Folge den Anweisungen...");
            System.out.println();
            while (true) {
                System.out.println("Name / Bezeichnung der Eigenschaft (fertigstellen mit Return): ");
                String property = in.readLine();
                if (property.isEmpty()) break;
                System.out.println("Inhalt der Eigenschaft: ");
                String content = in.readLine();
                properties.put(property, content);
            }

            properties.forEach((key, value) -> System.out.printf("key: %s, value: %s%n", key, value));

            saveCharacter(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCharacter(Map<String, String> properties) {
        final String id = UUID.randomUUID().toString();
        Database.JDBI.inTransaction(handle -> insertCharacter(handle, id, properties));
    }

    private Object insertCharacter(Handle handle, String id, Map<String, String> properties) {
        insertCharacterAnchor(handle, id);
        for(Map.Entry<String, String> entry : properties.entrySet()){
            insertProperty(handle, id, entry);
        }
        return null;
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
