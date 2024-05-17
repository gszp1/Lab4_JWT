package org.example.zadania_rest_api.service;

import org.example.zadania_rest_api.model.Department;
import org.example.zadania_rest_api.model.Employee;
import org.example.zadania_rest_api.repository.DepartmentRepository;
import org.example.zadania_rest_api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    public void addEmployeeToDepartment(Long departmentId, Employee employee) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        department.ifPresent(dpt -> {
            dpt.getEmployees().add(employee);
            employee.setDepartment(dpt);
            departmentRepository.save(dpt);
            employeeRepository.save(employee);
        });
    }

    public List<Employee> getAllEmployeesByDepartmentIdAndEmployeeLastName(Long departmentId, String lastName) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        return department.map(dpt -> dpt.getEmployees()
                .stream()
                .filter(employee -> employee.getLastName().equals(lastName))
                .collect(Collectors.toList())
        ).orElseGet(List::of);
    }

    public List<Employee> getAllEmployeesWithSalaryBetween(Long departmentId, BigDecimal minSalary, BigDecimal maxSalary) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        return department.map(value -> value
                .getEmployees()
                .stream()
                .filter(employee -> (employee.getSalary().compareTo(minSalary) >= 0) &&
                        (employee.getSalary().compareTo(maxSalary) <= 0))
                .collect(Collectors.toList())
        ).orElseGet(List::of);
    }

    public List<Department> getAllDepartmentsWithNamesStartingWith(char letter){
        return departmentRepository.findDepartmentsByFirstLetterOfName(letter);
    }

    public Page<Department> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    public List<Department> findAllOrderByNameDesc() {
        return departmentRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    public List<Department> findAllOrderByNameAsc() {
        return departmentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

}
