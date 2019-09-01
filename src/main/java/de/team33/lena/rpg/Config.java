package de.team33.lena.rpg;

import javax.sql.DataSource;
import java.util.function.Function;

public class Config {

    private final String dataSourceFactoryName = "de.team33.lena.rpg.MySQLDataSourceFactory";

    public static Config read() {
        // TODO: read a config file
        return new Config();
    }

    public final String getDBHost() {
        // properties map oder so
        return "localhost";
    }

    public final String getDBScheme() {
        return "test01";
    }

    public final String getUsername() {
        return "root";
    }

    public final String getPassword() {
        return null;
    }

    public String getDataSourceFactoryName() {
        return dataSourceFactoryName;
    }
}
