package org.example.zadania_rest_api.repository;

import org.example.zadania_rest_api.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department as d WHERE d.name LIKE ?1%")
    List<Department> findDepartmentsByFirstLetterOfName(char firstLetter);
}
