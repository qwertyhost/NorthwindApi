package com.sparta.northwindapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwindapi.dao.CustomerDAO;
import com.sparta.northwindapi.dto.CustomerDto;
import com.sparta.northwindapi.entity.Customer;
import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Region;
import com.sparta.northwindapi.entity.Territory;
import com.sparta.northwindapi.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NorthwindController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private RegionRepository regionRepo;

    @Autowired
    private TerritoryRepository territoryRepo;

    @Autowired
    private EmployeeterritoryRepository employeeterritoryRepo;

    @Autowired
    private CustomerRepository customerRepo;

    private ObjectMapper mapper;
    private HttpHeaders headers;

    @GetMapping("/employee")
    public List<Employee> getEmployees(){
        return employeeRepo.findAll();
    }

    @GetMapping("/region")
    public List<Region> getRegions(){
        return regionRepo.findAll();
    }

    @GetMapping("/employee/boss/{id}")
    public Employee getEmployeeBoss(@PathVariable Integer id){
        return employeeRepo.findById(id).get().getReportsTo();
    }

    @GetMapping("/employee/territory/{id}")
    public List<Territory> getEmployeeTerritory(@PathVariable Integer id){
        return employeeterritoryRepo.findAll().stream().filter(c-> c.getEmployeeID().getId() ==id).map(c->c.getTerritoryID()).toList();
    }

    @PatchMapping("/customer/{id}/{companyName}")
    public CustomerDto updateCustomerName(@PathVariable String id, @PathVariable String companyName) {
        CustomerDto customerDto = new CustomerDto(id, companyName, null, null, null, null, null, null, null, null, null);
        CustomerDAO customerDAO = new CustomerDAO(customerRepo);
        return customerDAO.update(customerDto);
    }

    @GetMapping("/customer/all")
    public List<Customer> getAllCustomers(){
        CustomerDAO customerDAO = new CustomerDAO(customerRepo);
        return customerDAO.readAll();
    }

    @PostMapping("/customer")
    public boolean newCustomer(String id,String companyName,String contactName,String contactTitle,String address,String city,String region,String postalCode,String country,String phone,String fax){
        CustomerDAO customerDAO= new CustomerDAO(customerRepo);
        CustomerDto c = new CustomerDto(id, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax);
        return customerDAO.create(c);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable String id){
        CustomerDAO customerDAO= new CustomerDAO(customerRepo);
        customerDAO.delete(id);
    }

    @GetMapping("/territory/{id}")
    public ResponseEntity<String> getTerritory(@PathVariable String id) {
        Optional<Territory> foundTerritory = territoryRepo.findById(id);
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundTerritory.isPresent()) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(foundTerritory.get()), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Territory not found\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }
}
