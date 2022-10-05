package com.sparta.northwindapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Order;
import com.sparta.northwindapi.entity.Region;
import com.sparta.northwindapi.entity.Territory;
import com.sparta.northwindapi.repo.EmployeeRepository;
import com.sparta.northwindapi.repo.OrderRepository;
import com.sparta.northwindapi.repo.RegionRepository;
import com.sparta.northwindapi.repo.TerritoryRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class NorthwindController {

    private final EmployeeRepository employeeRepository;
    private final TerritoryRepository territoryRepository;
    private final RegionRepository regionRepository;
    private final OrderRepository orderRepository;

    private ObjectMapper mapper;
    private HttpHeaders headers;

    public NorthwindController(EmployeeRepository employeeRepository, TerritoryRepository territoryRepository, RegionRepository regionRepository, OrderRepository orderRepository) {
        this.employeeRepository = employeeRepository;
        this.territoryRepository = territoryRepository;
        this.regionRepository = regionRepository;
        this.orderRepository = orderRepository;
        mapper = new ObjectMapper();
    }

    @GetMapping("/employee/all")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<String> getEmployee(@PathVariable int id) {
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
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

    @PostMapping("/employee")
    public ResponseEntity<String> addNewEmployee(@RequestBody Employee newEmployee) {
        Employee savedEmployee = employeeRepository.save(newEmployee);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (savedEmployee != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(savedEmployee), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Employee could not be added\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @DeleteMapping("/employee/remove/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") int id) {
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundEmployee.isPresent()) {
            employeeRepository.delete(foundEmployee.get());
            result = new ResponseEntity<>(
                        "Employee removed", headers,
                        HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Employee could not be removed\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @GetMapping("/region/all")
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<String> getRegion(@PathVariable int id) {
        Optional<Region> foundRegion = regionRepository.findById(id);
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

    @PostMapping("/region")
    public ResponseEntity<String> addNewRegion(@RequestBody Region newRegion) {
        Region savedRegion = regionRepository.save(newRegion);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (savedRegion != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(savedRegion), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Region could not be added\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @DeleteMapping("/region/remove/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable(name = "id") int id) {
        Optional<Region> foundRegion = regionRepository.findById(id);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundRegion.isPresent()) {
            regionRepository.delete(foundRegion.get());
            result = new ResponseEntity<>(
                    "Region removed", headers,
                    HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Region could not be removed\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @GetMapping("/territory/all")
    public List<Territory> getAllTerritories() {
        return territoryRepository.findAll();
    }

    @GetMapping("/territory/{id}")
    public ResponseEntity<String> getTerritory(@PathVariable String id) {
        Optional<Territory> foundTerritory = territoryRepository.findById(id);
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

    @PostMapping("/territory")
    public ResponseEntity<String> addNewTerritory(@RequestBody Territory newTerritory) {
        Territory savedTerritory = territoryRepository.save(newTerritory);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (savedTerritory != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(savedTerritory), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Territory could not be added\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @DeleteMapping("/territory/remove/{id}")
    public ResponseEntity<String> deleteTerritory(@PathVariable(name = "id") String id) {
        Optional<Territory> foundTerritory = territoryRepository.findById(id);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundTerritory.isPresent()) {
            territoryRepository.delete(foundTerritory.get());
            result = new ResponseEntity<>(
                    "Region removed", headers,
                    HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Territory could not be removed\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @GetMapping("/order/all")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<String> getOrder(@PathVariable int id) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundOrder.isPresent()) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(foundOrder.get()), headers,
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

    @PostMapping("/order")
    public ResponseEntity<String> addNewOrder(@RequestBody Order newOrder) {
        Order savedOrder = orderRepository.save(newOrder);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (savedOrder != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(savedOrder), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Order could not be added\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @DeleteMapping("/order/remove/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundOrder.isPresent()) {
            orderRepository.delete(foundOrder.get());
            result = new ResponseEntity<>(
                    "Region removed", headers,
                    HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Order could not be removed\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }
}
