package com.points.model;

public class Membership {

    private Integer memberId;
    private String program;

    public Integer getMemberId() {
        return memberId;
    }

    public Membership setMemberId(Integer memberId) {
        this.memberId = memberId;
        return this;
    }

    public String getProgram() {
        return program;
    }

    public Membership setProgram(String program) {
        this.program = program;
        return this;
    }
}
