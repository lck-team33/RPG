package de.team33.lena.rpg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

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
        Database.JDBI.inTransaction(handle -> {
            handle.createUpdate("INSERT INTO characters (ts_creation) VALUES(:ts)")
                    .bind("ts", System.currentTimeMillis())
                    .execute();
            return null;
        });
    }
}
