package de.team33.lena.rpg;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public class Config {

    public static Map<String, String> read(String configName) throws ConfigException {
        final ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream in = Config.class.getResourceAsStream("/config.json")) {
            //noinspection unchecked
            final Map<String, Map<String, String>> configs = objectMapper.readValue(in, Map.class);
            return Optional.ofNullable(configs.get(configName)).orElseThrow(() -> new ConfigException("coult not find configName <" +
                    configName +
                    "> in " +
                    configs.toString()));
        } catch (IOException e) {
            throw new ConfigException("could not read <" + configName + ">", e);
        }
    }
}
