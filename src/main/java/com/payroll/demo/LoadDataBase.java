package com.payroll.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDataBase.class);

    @Bean
    CommandLineRunner initDB(EmployeeRepository employeeRepository){
        return args -> {
            LOGGER.info("PRELOADING..." + employeeRepository.save(new Employee("harry", "wizard")));
            LOGGER.info("PREOLOADING..." + employeeRepository.save(new Employee("mikkel", "timetraveller")));
        };
    }

}
