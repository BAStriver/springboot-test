package com.bas.config;

import com.bas.event.UserInfoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserInfoListener {
    @EventListener
    public void handleDemoEvent(UserInfoEvent event) {
        System.out.println("UserInfoEvent: "+ event.getData());
    }
}
