package com.gerimedi.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {


    public static final String API_VERSION = "v1";

    public static void main(final String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }


}
