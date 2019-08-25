package de.team33.lena.rpg;

import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;
import java.util.function.Function;

public class Database {

    public static Jdbi JDBI = Jdbi.create(newDataSource());

    private static DataSource newDataSource() {
        final Config config = Config.read();
        final String dataSourceFactoryName = config.getDataSourceFactoryName();
        try {
            //noinspection unchecked
            final Function<Config, DataSource> dataSourceFactory =
                    (Function<Config, DataSource>) Class.forName(dataSourceFactoryName).newInstance();
            return dataSourceFactory.apply(config);
        } catch (final Exception caught) {
            throw new IllegalStateException("Cannot get an instance of " + dataSourceFactoryName);
        }
    }
}
