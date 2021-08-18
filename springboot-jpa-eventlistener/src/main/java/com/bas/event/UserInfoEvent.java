package com.bas.event;

import org.springframework.context.ApplicationEvent;

public class UserInfoEvent extends ApplicationEvent {
    private String data;

    public UserInfoEvent(Object source, String data) {
        super(source);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
