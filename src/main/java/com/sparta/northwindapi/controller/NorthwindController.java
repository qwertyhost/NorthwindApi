package com.sparta.northwindapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Region;
import com.sparta.northwindapi.entity.Territory;
import com.sparta.northwindapi.repo.EmployeeRepository;
import com.sparta.northwindapi.repo.RegionRepository;
import com.sparta.northwindapi.repo.TerritoryRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NorthwindController {

    private final EmployeeRepository employeeRepository;
    private final TerritoryRepository territoryRepository;
    private final RegionRepository regionRepository;

    private ObjectMapper mapper;
    private HttpHeaders headers;

    public NorthwindController(EmployeeRepository employeeRepository, TerritoryRepository territoryRepository, RegionRepository regionRepository) {
        this.employeeRepository = employeeRepository;
        this.territoryRepository = territoryRepository;
        this.regionRepository = regionRepository;
    }

    //employee
    @GetMapping("/employee/all")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<String> getEmployee(@PathVariable int id) {
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundEmployee.isPresent()) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(foundEmployee.get()), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Employee not found\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable int id){
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundEmployee.isPresent()) {
            employeeRepository.delete(foundEmployee.get());
            result = new ResponseEntity<>(
                    "Region "+id+" removed", headers,
                    HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Employee not found\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @PathVariable Employee employee){
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundEmployee.isPresent()) {
            employeeRepository.delete(foundEmployee.get());
            result = new ResponseEntity<>(
                    "Region "+id+" removed and replaced with "+ employee.toString(), headers,
                    HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Employee not found\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<String> createorupdateEmployee(@PathVariable int id, @PathVariable Employee employee){
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (!foundEmployee.isPresent()) {
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Employee is already present\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }
    //region
    @GetMapping("/region/all")
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<String> getRegion(@PathVariable int id) {
        Optional<Region> foundRegion = regionRepository.findById(id);
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundRegion.isPresent()) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(foundRegion.get()), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Region not found\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }
    //territory
    @GetMapping("/territory/all")
    public List<Territory> getAllTerritories() {
        return territoryRepository.findAll();
    }

    @GetMapping("/territory/{id}")
    public ResponseEntity<String> getTerritory(@PathVariable String id) {
        Optional<Territory> foundTerritory = territoryRepository.findById(id);
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
