package de.team33.lena.rpg;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;
import java.util.function.Supplier;

public class Database {

    public static Jdbi JDBI = Jdbi.create(Config.read().getDataSource());
}
