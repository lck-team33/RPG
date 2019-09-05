package de.team33.lena.rpg;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class MySQLDataSourceFactory implements Function<Map<String, String>, DataSource> {

    @Override
    public DataSource apply(final Map<String, String> config) {
        MysqlDataSource result = new MysqlDataSource();
        result.setUrl("jdbc:mysql://" + config.get("dbHost") + "/" + config.get("dbScheme") + "?serverTimezone=UTC");
        result.setUser(config.get("username"));
        Optional.ofNullable(config.get("password"))
                .filter(pwd -> !pwd.isEmpty())
                .ifPresent(result::setPassword);
        return result;
    }
}
