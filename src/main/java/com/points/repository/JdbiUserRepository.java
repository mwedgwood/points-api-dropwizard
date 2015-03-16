package com.points.repository;

import com.points.model.User;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.util.IntegerMapper;
import org.skife.jdbi.v2.util.TypedMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class JdbiUserRepository implements Repository<User> {

    private final DBI dbi;

    public JdbiUserRepository(DBI dbi) {
        this.dbi = dbi;
    }

    @Override
    public User findById(Serializable id) {
        return dbi.withHandle(handle -> handle.createQuery("select id, name from user where id = :id")
                .bind("id", id)
                .map(new UserMapper())
                .first());
    }

    @Override
    public Collection<User> find() {
        return dbi.withHandle(handle -> handle.createQuery("select id, name from user")
                .map(new UserMapper())
                .list());
    }

    @Override
    public void save(User entity) {
        entity.setId(dbi.withHandle(handle -> handle.createStatement("insert into user (name) values (:name)")
                .bind("name", entity.getName())
                .executeAndReturnGeneratedKeys(IntegerMapper.FIRST).first()));
    }

    @Override
    public void update(User entity) {
        dbi.withHandle(handle -> handle.createStatement("update user set name = :name where id = :id")
                .bind("naem", entity.getName())
                .bind("id", entity.getId()));
    }

    @Override
    public void delete(User entity) {
        dbi.withHandle(handle -> handle.createStatement("delete from user where id = :id")
                .bind("id", entity.getId())
                .execute());
    }

    public static class UserMapper extends TypedMapper<User> {

        @Override
        protected User extractByName(ResultSet r, String name) throws SQLException {
            return new User()
                    .setId(r.getInt("id"))
                    .setName(r.getString("name"));
        }

        @Override
        protected User extractByIndex(ResultSet r, int index) throws SQLException {
            return new User()
                    .setId(r.getInt(1))
                    .setName(r.getString(2));
        }
    }
}
