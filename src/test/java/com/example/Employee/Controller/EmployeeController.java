package com.example.Employee.Controller;

import com.example.Employee.Model.EmployeeModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<EmployeeModel> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(new EmployeeModel(101, "Vaishnavi", "Developer"));
        employees.add(new EmployeeModel(102, "Rahul", "Manager"));
    }

    @GetMapping
    public List<EmployeeModel> getEmployees() {
        return employees;
    }

    @GetMapping("/{employeeId}")
    public EmployeeModel getEmployee(@PathVariable int employeeId) {
        for (EmployeeModel employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    @PostMapping
    public EmployeeModel addEmployee(@RequestBody EmployeeModel employee) {
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{employeeId}")
    public EmployeeModel updateEmployee(
            @PathVariable int employeeId,
            @RequestBody EmployeeModel updatedEmployee) {

        for (EmployeeModel employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                employee.setName(updatedEmployee.getName());
                employee.setDesignation(updatedEmployee.getDesignation());
                return employee;
            }
        }

        return null;
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        for (EmployeeModel employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                employees.remove(employee);
                return "Employee deleted successfully";
            }
        }

        return "Employee not found";
    }
}