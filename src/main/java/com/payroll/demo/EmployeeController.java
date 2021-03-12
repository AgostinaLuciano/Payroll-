package com.payroll.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getOne(@PathVariable Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PostMapping
    public void saveOne(@RequestBody Employee employee) {
        employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public void replaceOne(@RequestBody Employee newEmployee, @PathVariable Long id) {
        employeeRepository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            employeeRepository.save(employee);
            return null;
        }).orElseGet(() -> {
            newEmployee.setID(id);
            employeeRepository.save(newEmployee);
            return null;
        });
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}

