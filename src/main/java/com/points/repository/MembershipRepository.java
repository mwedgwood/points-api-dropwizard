package com.points.repository;

import com.points.model.Membership;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.util.IntegerMapper;
import org.skife.jdbi.v2.util.TypedMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MembershipRepository implements Repository<Membership> {

    private final DBI dbi;

    public MembershipRepository(DBI dbi) {
        this.dbi = dbi;
    }

    public Collection<Membership> findByUserId(Serializable userId) {
        return dbi.withHandle(handle -> handle.createQuery("select id, name, user_id, member_id from membership where user_id = :userId")
                .bind("userId", userId)
                .map(new MembershipMapper())
                .list());
    }

    @Override
    public Membership findById(Serializable id) {
        return dbi.withHandle(handle -> handle.createQuery("select id, name, user_id, member_id from membership where id = :id")
                .bind("id", id)
                .map(new MembershipMapper())
                .first());
    }

    @Override
    public Collection<Membership> find() {
        return dbi.withHandle(handle -> handle.createQuery("select id, name, user_id, member_id from membership")
                .map(new MembershipMapper())
                .list());
    }

    @Override
    public void save(Membership entity) {
        entity.setId(dbi.withHandle(handle -> handle.createStatement("insert into membership (name, user_id, member_id) values (:name, :userId, :memberId)")
                .bind("name", entity.getProgram())
                .bind("userId", entity.getUserId())
                .bind("memberId", entity.getMemberId())
                .executeAndReturnGeneratedKeys(IntegerMapper.FIRST).first()));
    }

    @Override
    public void update(Membership entity) {
        dbi.withHandle(handle -> handle.createStatement("update membership set name = :name, user_id = :userId, member_id = :memberId where id = :id")
                .bind("name", entity.getProgram())
                .bind("userId", entity.getUserId())
                .bind("userId", entity.getMemberId())
                .bind("id", entity.getId()));
    }

    @Override
    public void delete(Membership entity) {
        dbi.withHandle(handle -> handle.createStatement("delete from membership where id = :id")
                .bind("id", entity.getId())
                .execute());
    }

    public static class MembershipMapper extends TypedMapper<Membership> {

        @Override
        protected Membership extractByName(ResultSet resultSet, String s) throws SQLException {
            return new Membership()
                    .setId(resultSet.getInt("id"))
                    .setProgram(resultSet.getString("name"))
                    .setUserId(resultSet.getInt("user_id"))
                    .setMemberId(resultSet.getInt("member_id"));
        }

        @Override
        protected Membership extractByIndex(ResultSet resultSet, int i) throws SQLException {
            return new Membership()
                    .setId(resultSet.getInt(1))
                    .setProgram(resultSet.getString(2))
                    .setUserId(resultSet.getInt(3))
                    .setMemberId(resultSet.getInt(4));
        }
    }
}
