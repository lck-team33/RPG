package de.team33.lena.rpg;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class PostgreSQLDataSourceFactory implements Function<Map<String, String>, DataSource> {

    @Override
    public DataSource apply(final Map<String, String> config) {
        PGSimpleDataSource result = new PGSimpleDataSource(); //veraltet? D:
        result.setServerName(config.get("dbHost")); // den timecode musste ich rausnehmen ... schlimm? D:
        result.setPortNumber(Integer.parseInt(config.get("port"))); // blöd, weil exception möglich? D:
        result.setDatabaseName(config.get("dbScheme"));
        result.setUser(config.get("username"));Optional.ofNullable(config.get("password"))
                .filter(pwd -> !pwd.isEmpty())
                .ifPresent(result::setPassword);
        return result;
    }
}
