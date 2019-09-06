package de.team33.lena.rpg;

import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;
import java.util.Map;
import java.util.function.Function;

public class Database {

    public static Jdbi JDBI = Jdbi.create(newDataSource());

    private static DataSource newDataSource() {
        final Map<String, String> config = Config.read("mySQL01");
        //final Map<String, String> config = Config.read("postgreSQL01");
        // wenn config jetzt leer ist, dann ist was schiefgelaufen... was tun?

        final String dataSourceFactoryName = config.get("dataSourceFactoryName");
        try {
            //noinspection unchecked
            final Function<Map<String, String>, DataSource> dataSourceFactory =
                    (Function<Map<String, String>, DataSource>) Class.forName(dataSourceFactoryName).newInstance();
            return dataSourceFactory.apply(config);
        } catch (final Exception caught) {
            throw new IllegalStateException("Cannot get an instance of " + dataSourceFactoryName);
        }
    }
}
