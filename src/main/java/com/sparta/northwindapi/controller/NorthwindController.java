package com.sparta.northwindapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Region;
import com.sparta.northwindapi.entity.Territory;
import com.sparta.northwindapi.repo.EmployeeRepository;
import com.sparta.northwindapi.repo.EmployeeterritoryRepository;
import com.sparta.northwindapi.repo.RegionRepository;
import com.sparta.northwindapi.repo.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
