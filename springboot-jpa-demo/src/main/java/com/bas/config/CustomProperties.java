package com.bas.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "com.bas.custom")
@Data
public class CustomProperties {
    private String field1;
    private int field2;
    private boolean field3;
    private Map field4;

}