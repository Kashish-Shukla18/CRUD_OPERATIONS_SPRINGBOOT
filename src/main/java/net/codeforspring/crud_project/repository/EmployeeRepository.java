package net.codeforspring.crud_project.repository;

import net.codeforspring.crud_project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
