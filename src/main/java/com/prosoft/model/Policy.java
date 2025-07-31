package com.prosoft.model;

public class Policy {
    private String id;
    private PolicyState state;
    private int retryCount = 0;
    private String lastError = "";

    public Policy(String id) {
        this.id = id;
        this.state = PolicyState.STATE1;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public PolicyState getState() { return state; }
    public void setState(PolicyState state) { this.state = state; }

    public int getRetryCount() { return retryCount; }
    public void setRetryCount(int retryCount) { this.retryCount = retryCount; }

    public String getLastError() { return lastError; }
    public void setLastError(String lastError) { this.lastError = lastError; }

    @Override
    public String toString() {
        return "Policy{id='" + id + "', state=" + state +
                ", retryCount=" + retryCount + ", lastError='" + lastError + "'}";
    }
}