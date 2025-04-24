package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// Main class to start the Spring Boot application
@SpringBootApplication
@ComponentScan(basePackages = {"java","controller","model","service"})
public class ContainerPlacementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContainerPlacementApplication.class, args);
    }
}