package com.filrouge.designflow.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//annotation for enabling async process (response time match faster)
@EnableAsync
public class SpringFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFlowApplication.class, args);
    }

}
