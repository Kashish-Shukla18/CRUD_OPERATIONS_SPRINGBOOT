package net.codeforspring.crud_project.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.codeforspring.crud_project.entity.Employee;
import net.codeforspring.crud_project.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employeedata")
    public Employee postEmployee(@RequestBody Employee employee){
        return employeeService.postEmployee(employee);
    }
    @GetMapping("/allemployees")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee with ID "+ id + " deleted successfully", HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        Employee employee=employeeService.getEmployeeById(id);
        if (employee==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employee);
    }
    @PatchMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id,@RequestBody Employee employee){
        Employee upadtedemployee=employeeService.updateEmployee(id,employee);
        if (upadtedemployee==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(upadtedemployee);
    }
}
