package de.team33.lena.rpg;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Config {

    public static Map<String, String> read(String configName) {
        File file = new File("ressources/config.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String, String>> configs = null;
        try {
            configs = objectMapper.readValue(file, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configs.get(configName);
    }
}
