package de.team33.lena.rpg;

import javax.sql.DataSource;
import java.util.function.Function;

public class Config {

    private final String dataSourceFactoryName = "de.team33.lena.rpg.MySQLDataSourceFactory";

    public static Config read() {
        // TODO: read a config file
        return new Config();
    }

    public final DataSource getDataSource() {
        try {
            //noinspection unchecked
            final Function<Config, DataSource> dataSourceFactory =
                    (Function<Config, DataSource>) Class.forName(dataSourceFactoryName).newInstance();
            return dataSourceFactory.apply(this);
        } catch (final Exception caught) {
            throw new IllegalStateException("Cannot get an instance of " + dataSourceFactoryName);
        }
    }

    public final String getDBHost() {
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
}
