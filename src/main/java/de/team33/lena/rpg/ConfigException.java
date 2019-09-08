package de.team33.lena.rpg;

public class ConfigException extends Exception {

    public ConfigException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ConfigException(final String message) {
        super(message);
    }
}
