package com.uqac.controller;

public class AccountAction {

    public String action = "deposer";
    public float value = 0f;

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
