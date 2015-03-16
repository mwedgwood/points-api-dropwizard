package com.points.repository;

import com.points.model.User;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.util.IntegerMapper;
import org.skife.jdbi.v2.util.TypedMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class UserRepository implements Repository<User> {

    private final DBI dbi;
    private final MembershipRepository membershipRepository;

    public UserRepository(DBI dbi) {
        this.dbi = dbi;
        this.membershipRepository = new MembershipRepository(dbi);
    }

    public User findByName(String name) {
        User user = dbi.withHandle(handle -> handle.createQuery("select id, name from user where name = :name")
                .bind("name", name)
                .map(new UserMapper())
                .first());

        return user != null ? user.addMemberships(membershipRepository.findByUserId(user.getId())) : user;
    }

    @Override
    public User findById(Serializable id) {
        User user = dbi.withHandle(handle -> handle.createQuery("select id, name from user where id = :id")
                .bind("id", id)
                .map(new UserMapper())
                .first());

        return user != null ? user.addMemberships(membershipRepository.findByUserId(user.getId())) : user;
    }

    @Override
    public Collection<User> find() {
        List<User> users = dbi.withHandle(handle -> handle.createQuery("select id, name from user")
                .map(new UserMapper())
                .list());

        for (User user : users) {
            user.addMemberships(membershipRepository.findByUserId(user.getId()));
        }
        return users;
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
                .bind("name", entity.getName())
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
        protected User extractByName(ResultSet resultSet, String name) throws SQLException {
            return new User()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"));
        }

        @Override
        protected User extractByIndex(ResultSet resultSet, int index) throws SQLException {
            return new User()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2));
        }
    }
}
