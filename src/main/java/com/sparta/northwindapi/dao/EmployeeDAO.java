package com.sparta.northwindapi.dao;

import com.sparta.northwindapi.dto.EmployeeDTO;
import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.repo.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeDAO {
    private final EmployeeRepository REPO;

    public EmployeeDAO(EmployeeRepository repo) {
        this.REPO = repo;
    }

    public EmployeeDTO getEmployeeById(int id) {
        Optional<Employee> optional = REPO.findById(id);
        Employee employee;
        if (optional.isPresent())
            employee = optional.get();
        else
            return null;
        return new EmployeeDTO();
    }

    public long getEmployeeAmount(int id) {
        return REPO.count();
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = REPO.findAll();
        if (employees.isEmpty())
            return null;
        return employees.stream().map(r -> new EmployeeDTO()).toList();
    }
    public EmployeeDTO convertEmployee(Employee employee) {
        EmployeeDTO converted = new EmployeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getTitle(), employee.getTitleOfCourtesy(), employee.getBirthDate(), employee.getHireDate(), employee.getAddress(), employee.getCity(), employee.getRegion(), employee.getPostalCode(), employee.getCountry(), employee.getHomePhone(), employee.getExtension(), employee.getPhoto(), employee.getNotes(), employee.getReportsTo(), employee.getPhotoPath(), employee.getSalary(), employee.getEmployees(),employee.getOrders());
        return converted;
    }
}