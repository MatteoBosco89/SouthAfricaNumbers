package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"service", "controller", "model"})
@EntityScan(basePackages = {"model"})
@EnableJpaRepositories(basePackages = {"model"})
public class Application {
    public static void main(String[] args) {
    	System.setProperty("server.servlet.context-path", "/SouthAfricaNum");
        SpringApplication.run(Application.class, args);
    }

}
