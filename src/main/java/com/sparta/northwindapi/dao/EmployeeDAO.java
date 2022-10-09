package com.sparta.northwindapi.dao;

import com.sparta.northwindapi.dto.EmployeeDTO;
import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Order;
import com.sparta.northwindapi.repo.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeDAO {
    private final EmployeeRepository REPO;

    public EmployeeDAO(EmployeeRepository repo) {
        this.REPO = repo;
    }

    public EmployeeDTO get(int id) {
        Optional<Employee> optional = REPO.findById(id);
        Employee employee;
        if (optional.isPresent())
            employee = optional.get();
        else
            return null;
        return getEmployeeDTO(employee);
    }

    public EmployeeDTO create(EmployeeDTO employee) {
        boolean exists = this.get(employee.getId()) != null;
        if (exists)
            return null;
        Employee toInsert = new Employee();
        toInsert.setId(employee.getId());
        return getEmployeeDTO(toInsert, toInsert);
    }

    public EmployeeDTO update(int id) {
        boolean exists = this.get(id) != null;
        if (!exists)
            return null;
        Employee toInsert = new Employee();
        toInsert.setId(toInsert.getId());
        return getEmployeeDTO(toInsert, toInsert);

    }

    public EmployeeDTO updateOrCreate(EmployeeDTO updated, int id) {
        boolean exists = this.get(id) != null;
        if (exists)
            return update(id);
        return create(updated);
    }

    private EmployeeDTO getEmployeeDTO(EmployeeDTO updated, Employee toInsert) {
        toInsert.setLastName(updated.getLastName());
        toInsert.setFirstName(updated.getFirstName());
        toInsert.setTitle(updated.getTitle());
        toInsert.setTitleOfCourtesy(updated.getTitleOfCourtesy());
        toInsert.setBirthDate(updated.getBirthDate());
        toInsert.setHireDate(updated.getHireDate());
        toInsert.setAddress(updated.getAddress());
        toInsert.setCity(updated.getCity());
        toInsert.setRegion(updated.getRegion());
        toInsert.setPostalCode(updated.getPostalCode());
        toInsert.setCountry(updated.getCountry());
        toInsert.setHomePhone(updated.getHomePhone());
        toInsert.setExtension(updated.getExtension());
        toInsert.setPhoto(updated.getPhoto());
        toInsert.setNotes(updated.getNotes());
        toInsert.setReportsTo(updated.getReportsTo());
        toInsert.setPhotoPath(updated.getPhotoPath());
        toInsert.setSalary(updated.getSalary());
        toInsert.setEmployees(updated.getEmployees());
        toInsert.setOrders(updated.getOrders());
        toInsert.setTerritories(toInsert.getTerritories());

        Employee inserted = REPO.save(toInsert);
        return getEmployeeDTO(inserted);
    }

    private EmployeeDTO getEmployeeDTO(Employee inserted) {
        return getEmployeeDTO(inserted);
    }

    public int delete(int id) {
        Optional<Employee> optional = REPO.findById(id);
        if (optional.isPresent()) {
            REPO.delete(optional.get());
            return optional.get().getId();
        }
        else {
            return -1;
        } }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = REPO.findAll();
        if (employees.isEmpty())
            return null;
        return employees.stream().map(r -> new EmployeeDTO()).toList();
    }
    public EmployeeDTO convertEmployee(Employee employee) {
        EmployeeDTO converted = new EmployeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getTitle(), employee.getTitleOfCourtesy(), employee.getBirthDate(), employee.getHireDate(), employee.getAddress(), employee.getCity(), employee.getRegion(), employee.getPostalCode(), employee.getCountry(), employee.getHomePhone(), employee.getExtension(), employee.getPhoto(), employee.getNotes(), employee.getReportsTo(), employee.getPhotoPath(), employee.getSalary(), employee.getEmployees(),employee.getOrders(),employee.getTerritories());
        return converted;
    }
}