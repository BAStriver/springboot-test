package com.bas;

import com.bas.event.UserInfoEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SpringbootApplication {
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
        context.publishEvent(new UserInfoEvent(context, "test"));
    }
}
