package com.prosoft.model;

public class Policy {
    private String id;
    private PolicyState state;

    public Policy(String id) {
        this.id = id;
        this.state = PolicyState.STATE1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PolicyState getState() {
        return state;
    }

    public void setState(PolicyState state) {
        this.state = state;
    }
}
