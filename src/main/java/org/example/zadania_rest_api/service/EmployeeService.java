package org.example.zadania_rest_api.service;

import org.example.zadania_rest_api.model.Employee;
import org.example.zadania_rest_api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> findBySalaryBetween(BigDecimal min, BigDecimal max) {
        return employeeRepository.findBySalaryBetween(min, max);
    }

    public List<Employee> findAllOrderBySalaryDesc() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "salary"));
    }

    public List<Employee> findAllOrderBySalaryAsc() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "salary"));
    }

    public List<Employee> findAllByFirstLetterOfLastName(char firstLetter) {
        return employeeRepository.findAllByFirstLetterOfLastName(firstLetter);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
