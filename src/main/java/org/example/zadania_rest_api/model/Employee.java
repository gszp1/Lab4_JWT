package org.example.zadania_rest_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(name = "employment_date", nullable = false)
    private LocalDate employmentDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String firstName, String lastName, BigDecimal salary,
                    LocalDate employmentDate, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.employmentDate = employmentDate;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", employmentDate=" + employmentDate +
                '}';
    }
}
