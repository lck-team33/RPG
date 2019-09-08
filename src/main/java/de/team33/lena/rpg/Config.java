package de.team33.lena.rpg;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Config {

    public static Map<String, String> read(String configName) {
        final File file = new File("ressources/config.json");
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            //noinspection unchecked
            final Map<String, Map<String, String>> configs = objectMapper.readValue(file, Map.class);
            return configs.get(configName);
        } catch (IOException e) {
            throw new IllegalArgumentException("could not read <" + configName + ">", e);
        }
    }
}
