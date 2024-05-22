package org.example.zadania_rest_api.controller;

import org.example.zadania_rest_api.model.Department;
import org.example.zadania_rest_api.model.Employee;
import org.example.zadania_rest_api.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PreAuthorize("hasAuthority('guest:read')")
    @GetMapping("/{id}")
    public Optional<Department> getDepartmentByIdPath(@PathVariable("id") Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PreAuthorize("hasAuthority('guest:read')")
    @GetMapping("/id")
    public Optional<Department> getDepartmentByIdParam(@RequestParam("id") Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/delete")
    public void deleteDepartmentById(@RequestParam("id") Long id) {
        departmentService.deleteDepartmentById(id);
    }

    @PreAuthorize("hasAuthority('admin:update')")
    @PutMapping("/update")
    public Department updateDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/{departmentId}/addEmployee")
    public void addEmployeeToDepartment(@PathVariable("departmentId") Long departmentId,
                                        @RequestBody Employee employee) {
        departmentService.addEmployeeToDepartment(departmentId, employee);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/{id}/allByEmployeeSecondName")
    public List<Employee> getAllByEmployeeSecondName(@PathVariable("id") Long id, @RequestParam("name") String name) {
        return departmentService.getAllEmployeesByDepartmentIdAndEmployeeLastName(id, name);
    }

    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping("/{id}/allWithSalaryBetween")
    public List<Employee> getAllBySalaryBetween(@PathVariable("id") Long id,
                                                @RequestParam("minSalary") BigDecimal minSalary,
                                                @RequestParam("maxSalary") BigDecimal maxSalary) {
        return departmentService.getAllEmployeesWithSalaryBetween(id, minSalary, maxSalary);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/all/nameFirstLetter")
    public List<Department> getAllDepartmentsByFirstLetter(@RequestParam("firstLetter") char firstLetter) {
        return departmentService.getAllDepartmentsWithNamesStartingWith(firstLetter);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/all/paginated")
    public Page<Department> getDepartmentsPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        return departmentService.findAll(PageRequest.of(page, size));
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/all/nameSortedDesc")
    public List<Department> getAllOrderByNameDesc() {
        return departmentService.findAllOrderByNameDesc();
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/all/nameSortedAsc")
    public List<Department> getAllOrderByNameAsc() {
        return departmentService.findAllOrderByNameAsc();
    }
}
