package com.points.model;

import java.util.ArrayList;
import java.util.Collection;

public class User {

    private Integer id;
    private String name;
    private Collection<Membership> memberships = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Collection<Membership> getMemberships() {
        return memberships;
    }

    public User setMemberships(Collection<Membership> memberships) {
        this.memberships = memberships;
        return this;
    }
}
