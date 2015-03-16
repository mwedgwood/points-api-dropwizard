package com.points.repository;

import org.h2.jdbcx.JdbcDataSource;
import org.skife.jdbi.v2.DBI;

public class JdbiUtil {

    private DBI dbi;

    private JdbiUtil() {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:~/points_test");
        dbi = new DBI(jdbcDataSource);
    }

    private static class SingletonHolder {
        private static final JdbiUtil INSTANCE = new JdbiUtil();
    }

    public static DBI getDbi() {
        return SingletonHolder.INSTANCE.dbi;
    }


}
