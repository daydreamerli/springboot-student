package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student jhon =new Student(
                    "John",
                    "jhon@test.com",
                    LocalDate.of(2000,5,5)
            );
            Student mike =new Student(
                    "Mike",
                    "Mike@test.com",
                    LocalDate.of(2001,6,6)
            );

            repository.saveAll(
                    List.of(jhon,mike)
            );
        };

    };
}
