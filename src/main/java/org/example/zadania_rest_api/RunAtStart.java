package org.example.zadania_rest_api;

import org.example.zadania_rest_api.model.Department;
import org.example.zadania_rest_api.model.Employee;
import org.example.zadania_rest_api.repository.DepartmentRepository;
import org.example.zadania_rest_api.repository.EmployeeRepository;
import org.example.zadania_rest_api.util.Role;
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
                    Department.builder()
                            .name("SoftwareDevelopment")
                            .description("DepartmentDescription")
                            .budget(new BigDecimal("100000.0"))
                            .establishmentDate(LocalDate.of(1999, 12, 1))
                            .build(),
                    Department.builder()
                            .name("DataAnalysis")
                            .description("DepartmentDescription")
                            .budget(new BigDecimal("2000000"))
                            .establishmentDate(LocalDate.of(2001, 1, 12))
                            .build()
            );
            departments = departmentRepository.saveAll(departments);

            List<Employee> employees = Arrays.asList(
                    Employee.builder()
                            .firstName("Tomasz")
                            .lastName("Nowak")
                            .email("TNowak@mail.com")
                            .employmentDate(LocalDate.of(2024, 3, 2))
                            .salary(new BigDecimal("4500.00"))
                            .department(departments.getFirst())
                            .password("password")
                            .role(Role.ADMIN)
                            .build(),
                    Employee.builder()
                            .firstName("Jakub")
                            .lastName("Kowalski")
                            .salary(new BigDecimal("3699.45"))
                            .department(departments.getFirst())
                            .email("JakubKowalski@mail.com")
                            .employmentDate(LocalDate.of(2019, 12, 23))
                            .password("password")
                            .role(Role.USER)
                            .build(),
                    Employee.builder()
                            .firstName("Jakub")
                            .lastName("Kowalski")
                            .salary(new BigDecimal("7000.4"))
                            .employmentDate(LocalDate.of(2000, 7, 4))
                            .email("JKowalski@mail.com")
                            .password("password")
                            .department(departments.get(1))
                            .role(Role.USER)
                            .build(),
                    Employee.builder()
                            .firstName("Andrzej")
                            .lastName("Nowak")
                            .salary(new BigDecimal("2132.2"))
                            .email("AndrzejNowak@gmail.com")
                            .password("password")
                            .role(Role.ADMIN)
                            .employmentDate(LocalDate.of(2012, 12, 12))
                            .department(departments.get(1))
                            .build()
            );

            departments.get(0).getEmployees().addAll(employees.subList(0, 2));
            departments.get(1).getEmployees().addAll(employees.subList(2, 4));

            employeeRepository.saveAll(employees);
            departmentRepository.saveAll(departments);
        };
    }
}
