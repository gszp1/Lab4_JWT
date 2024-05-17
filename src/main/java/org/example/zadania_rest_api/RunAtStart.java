package org.example.zadania_rest_api;

import org.example.zadania_rest_api.model.Department;
import org.example.zadania_rest_api.model.Employee;
import org.example.zadania_rest_api.repository.DepartmentRepository;
import org.example.zadania_rest_api.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class RunAtStart {

    @Bean
    public CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository,
                                               DepartmentRepository departmentRepository) {
        return args -> {
            List<Department> departments = Arrays.asList(
                    new Department(
                            "SoftwareDevelopment",
                            "DepartmentDescription",
                            new BigDecimal("100000.0"),
                            LocalDate.of(1999, 12, 1)
                    ),
                    new Department(
                            "DataAnalysis",
                            "DepartmentDescription",
                            new BigDecimal("200000"),
                            LocalDate.of(2001, 1, 12)
                    )
            );
            departments = departmentRepository.saveAll(departments);

            List<Employee> employees = Arrays.asList(
                    new Employee(
                            "Tomasz",
                            "Nowak",
                            new BigDecimal("4500.00"),
                            LocalDate.of(2024, 3, 2),
                            departments.getFirst()
                    ),
                    new Employee(
                            "Jan",
                            "Kowalski",
                            new BigDecimal("3699.45"),
                            LocalDate.of(2019, 12, 23),
                            departments.getFirst()
                    ),
                    new Employee(
                            "Jakub",
                            "Kowalski",
                            new BigDecimal("7000.4"),
                            LocalDate.of(2000, 7, 4),
                            departments.get(1)
                    ),
                    new Employee(
                            "Andrzej",
                            "Nowak",
                            new BigDecimal("2132.2"),
                            LocalDate.of(2012, 12, 12),
                            departments.get(1)
                    )
            );

            departments.get(0).getEmployees().addAll(employees.subList(0, 2));
            departments.get(1).getEmployees().addAll(employees.subList(2, 4));

            employeeRepository.saveAll(employees);
            departmentRepository.saveAll(departments);
        };
    }
}
