package de.team33.lena.rpg;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.function.Function;

public class MySQLDataSourceFactory implements Function<Config, DataSource> {

    @Override
    public DataSource apply(final Config config) {
        // properties map oder so
        MysqlDataSource result = new MysqlDataSource();
        result.setUrl("jdbc:mysql://" + config.getDBHost() + "/" + config.getDBScheme() + "?serverTimezone=UTC");
        result.setUser(config.getUsername());
        Optional.ofNullable(config.getPassword())
                .filter(pwd -> !pwd.isEmpty())
                .ifPresent(result::setPassword);
        return result;
    }
}
