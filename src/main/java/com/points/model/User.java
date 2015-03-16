package com.points.model;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

    @NotNull
    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Collection<Membership> getMemberships() {
        return Collections.unmodifiableCollection(memberships);
    }

    public User addMemberships(Collection<Membership> memberships) {
        this.memberships.addAll(memberships);
        return this;
    }
}
