package com.uqac.controller;

public class AccountAction {

    private String action;
    private float value;

    public float getValue() {
        return value;
    }

    public String getAction() {
        return action;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
