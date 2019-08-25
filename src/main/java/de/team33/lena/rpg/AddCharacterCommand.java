package de.team33.lena.rpg;

import org.jdbi.v3.core.Handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class AddCharacterCommand implements Runnable {

    private static final StorageService STORAGE_SERVICE = new JdbiStorageService();

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

            final String id = STORAGE_SERVICE.insertCharacter(properties);
            System.out.println("Neue ID des Characters: " + id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
