package com.welisit.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author welisit
 * @Description TODO
 * @create 2020-06-13 8:36
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.welisit"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
