package com.bas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableAutoConfiguration
//@EnableTransactionManagement
//@ServletComponentScan
//@ComponentScan
//@EnableScheduling
public class SpringbootApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
