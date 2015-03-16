package com.points.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class Membership {

    private Integer id;
    private Integer userId;
    private Integer memberId;
    private String program;

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public Membership setId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonIgnore
    public Integer getUserId() {
        return userId;
    }

    public Membership setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @NotNull
    public Integer getMemberId() {
        return memberId;
    }

    public Membership setMemberId(Integer memberId) {
        this.memberId = memberId;
        return this;
    }

    @NotNull
    public String getProgram() {
        return program;
    }

    public Membership setProgram(String program) {
        this.program = program;
        return this;
    }
}
