package de.team33.lena.rpg;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Config {

    public static Map<String, String> read(String configName) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream in = Config.class.getResourceAsStream("/config.json")) {
            //noinspection unchecked
            final Map<String, Map<String, String>> configs = objectMapper.readValue(in, Map.class);
            return configs.get(configName);
        } catch (IOException e) {
            throw new IllegalArgumentException("could not read <" + configName + ">", e);
        }
    }
}
