package de.team33.lena.rpg;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;

public class Database {
    public static Jdbi JDBI = Jdbi.create(newDatasource());

    private static DataSource newDatasource() {
        MysqlDataSource result = new MysqlDataSource();
        result.setUrl("jdbc:mysql://localhost/test01?serverTimezone=UTC");
        result.setUser("root");
        //result.setPassword();
        return result;
    }
}
