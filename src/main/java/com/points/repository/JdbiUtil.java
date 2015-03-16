package com.points.repository;

import org.h2.jdbcx.JdbcDataSource;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbiUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbiUtil.class);

    private static final String CREATE_TABLES = "\nDROP TABLE IF EXISTS user;\n" +
            "DROP TABLE IF EXISTS membership;\n" +
            "CREATE TABLE user (\n" +
            "  id      SERIAL PRIMARY KEY,\n" +
            "  name    VARCHAR(50) NOT NULL\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE membership (\n" +
            "  id           SERIAL PRIMARY KEY,\n" +
            "  name         VARCHAR(50) NOT NULL,\n" +
            "  member_id    INT NOT NULL,\n" +
            "  user_id      INT REFERENCES user(id)\n" +
            ");\n";

    private DBI dbi;

    private JdbiUtil() {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:~/points_test");
        dbi = new DBI(jdbcDataSource);

        resetDatabase();
    }

    private void resetDatabase() {
        dbi.withHandle(handle -> handle.createStatement(CREATE_TABLES).execute());
        LOGGER.info(CREATE_TABLES);
    }

    private static class SingletonHolder {
        private static final JdbiUtil INSTANCE = new JdbiUtil();
    }

    public static DBI getDbi() {
        return SingletonHolder.INSTANCE.dbi;
    }

    public static void bootstrap() {
        SingletonHolder.INSTANCE.resetDatabase();
    }

}
