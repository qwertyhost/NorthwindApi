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

    public EmployeeDTO get(int id) {
        Optional<Employee> optional = REPO.findById(id);
        Employee employee;
        if (optional.isPresent())
            employee = optional.get();
        else
            return null;
        return new EmployeeDTO();
    }

    public EmployeeDTO create(EmployeeDTO employee) {
        boolean exists = this.get(employee.getId()) != null;
        if (exists)
            return null;
        Employee toInsert = new Employee();
        toInsert.setId(employee.getId());
        toInsert.setLastName(toInsert.getLastName());
        toInsert.setFirstName(toInsert.getFirstName());
        toInsert.setTitle(toInsert.getTitle());
        toInsert.setTitleOfCourtesy(toInsert.getTitleOfCourtesy());
        toInsert.setBirthDate(toInsert.getBirthDate());
        toInsert.setHireDate(toInsert.getHireDate());
        toInsert.setAddress(toInsert.getAddress());
        toInsert.setCity(toInsert.getCity());
        toInsert.setRegion(toInsert.getRegion());
        toInsert.setPostalCode(toInsert.getPostalCode());
        toInsert.setCountry(toInsert.getCountry());
        toInsert.setHomePhone(toInsert.getHomePhone());
        toInsert.setExtension(toInsert.getExtension());
        toInsert.setPhoto(toInsert.getPhoto());
        toInsert.setNotes(toInsert.getNotes());
        toInsert.setReportsTo(toInsert.getReportsTo());
        toInsert.setPhotoPath(toInsert.getPhotoPath());
        toInsert.setSalary(toInsert.getSalary());
        toInsert.setEmployees(toInsert.getEmployees());
        toInsert.setOrders(toInsert.getOrders());

        Employee inserted = REPO.save(toInsert);
        return new EmployeeDTO(inserted.getId(), inserted.getFirstName(), inserted.getLastName(), inserted.getTitle(), inserted.getTitleOfCourtesy(), inserted.getBirthDate(), inserted.getHireDate(), inserted.getAddress(), inserted.getCity(), inserted.getRegion(), inserted.getPostalCode(), inserted.getCountry(), inserted.getHomePhone(), inserted.getExtension(), inserted.getPhoto(), inserted.getNotes(), inserted.getReportsTo(), inserted.getPhotoPath(), inserted.getSalary(), inserted.getEmployees(),inserted.getOrders());
    }

    public EmployeeDTO update(EmployeeDAO updated, int id) {
        boolean exists = this.get(id) != null;
        if (!exists)
            return null;
        Employee toInsert = new Employee();
        toInsert.setId(toInsert.getId());
        toInsert.setLastName(toInsert.getLastName());
        toInsert.setFirstName(toInsert.getFirstName());
        toInsert.setTitle(toInsert.getTitle());
        toInsert.setTitleOfCourtesy(toInsert.getTitleOfCourtesy());
        toInsert.setBirthDate(toInsert.getBirthDate());
        toInsert.setHireDate(toInsert.getHireDate());
        toInsert.setAddress(toInsert.getAddress());
        toInsert.setCity(toInsert.getCity());
        toInsert.setRegion(toInsert.getRegion());
        toInsert.setPostalCode(toInsert.getPostalCode());
        toInsert.setCountry(toInsert.getCountry());
        toInsert.setHomePhone(toInsert.getHomePhone());
        toInsert.setExtension(toInsert.getExtension());
        toInsert.setPhoto(toInsert.getPhoto());
        toInsert.setNotes(toInsert.getNotes());
        toInsert.setReportsTo(toInsert.getReportsTo());
        toInsert.setPhotoPath(toInsert.getPhotoPath());
        toInsert.setSalary(toInsert.getSalary());
        toInsert.setEmployees(toInsert.getEmployees());
        toInsert.setOrders(toInsert.getOrders());

        Employee inserted = REPO.save(toInsert);
        return new EmployeeDTO(inserted.getId(), inserted.getFirstName(), inserted.getLastName(), inserted.getTitle(), inserted.getTitleOfCourtesy(), inserted.getBirthDate(), inserted.getHireDate(), inserted.getAddress(), inserted.getCity(), inserted.getRegion(), inserted.getPostalCode(), inserted.getCountry(), inserted.getHomePhone(), inserted.getExtension(), inserted.getPhoto(), inserted.getNotes(), inserted.getReportsTo(), inserted.getPhotoPath(), inserted.getSalary(), inserted.getEmployees(),inserted.getOrders());

    }

    public EmployeeDTO updateOrCreate(Employee updated, int id) {
        Employee toInsert = new Employee();
        toInsert.setId(id);
        toInsert.setLastName(toInsert.getLastName());
        toInsert.setFirstName(toInsert.getFirstName());
        toInsert.setTitle(toInsert.getTitle());
        toInsert.setTitleOfCourtesy(toInsert.getTitleOfCourtesy());
        toInsert.setBirthDate(toInsert.getBirthDate());
        toInsert.setHireDate(toInsert.getHireDate());
        toInsert.setAddress(toInsert.getAddress());
        toInsert.setCity(toInsert.getCity());
        toInsert.setRegion(toInsert.getRegion());
        toInsert.setPostalCode(toInsert.getPostalCode());
        toInsert.setCountry(toInsert.getCountry());
        toInsert.setHomePhone(toInsert.getHomePhone());
        toInsert.setExtension(toInsert.getExtension());
        toInsert.setPhoto(toInsert.getPhoto());
        toInsert.setNotes(toInsert.getNotes());
        toInsert.setReportsTo(toInsert.getReportsTo());
        toInsert.setPhotoPath(toInsert.getPhotoPath());
        toInsert.setSalary(toInsert.getSalary());
        toInsert.setEmployees(toInsert.getEmployees());
        toInsert.setOrders(toInsert.getOrders());

        Employee inserted = REPO.save(toInsert);
        return new EmployeeDTO(inserted.getId(), inserted.getFirstName(), inserted.getLastName(), inserted.getTitle(), inserted.getTitleOfCourtesy(), inserted.getBirthDate(), inserted.getHireDate(), inserted.getAddress(), inserted.getCity(), inserted.getRegion(), inserted.getPostalCode(), inserted.getCountry(), inserted.getHomePhone(), inserted.getExtension(), inserted.getPhoto(), inserted.getNotes(), inserted.getReportsTo(), inserted.getPhotoPath(), inserted.getSalary(), inserted.getEmployees(),inserted.getOrders());
    }

    public long getEmployeeAmount(int id) {
        return REPO.count();
    }

    public void delete(int id) { REPO.deleteById(id); }

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