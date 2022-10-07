package com.sparta.northwindapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwindapi.dao.*;
import com.sparta.northwindapi.dto.*;
import com.sparta.northwindapi.entity.*;
import com.sparta.northwindapi.repo.EmployeeRepository;
import com.sparta.northwindapi.repo.RegionRepository;
import com.sparta.northwindapi.repo.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private RegionDAO regionDAO;

    private final OrderDAO orderDAO;
    private final SupplierDAO supplierDAO;

    private ObjectMapper mapper;
    private HttpHeaders headers;

    public NorthwindController(EmployeeRepository employeeRepository, TerritoryRepository territoryRepository, RegionRepository regionRepository, OrderDAO orderDAO, SupplierDAO supplierDAO) {
        this.employeeRepository = employeeRepository;
        this.territoryRepository = territoryRepository;
        this.regionRepository = regionRepository;
        this.orderDAO = orderDAO;
        this.supplierDAO = supplierDAO;
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
    public ResponseEntity<String> getAllRegions() {
        List<RegionDTO> regions = regionDAO.getAll();
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result;
        if (regions != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(regions), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            result = new ResponseEntity<>("{\"message\":\"Regions not found\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<String> getRegion(@PathVariable int id) {
        RegionDTO region = regionDAO.get(id);
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result;
        if (region != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(region), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            result = new ResponseEntity<>("{\"message\":\"Region not found\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @PostMapping("/region")
    public ResponseEntity<String> createRegion(@RequestBody RegionDTO region) {
        RegionDTO inserted = regionDAO.create(region);
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result;
        if (inserted != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(inserted), headers,
                            HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            result = new ResponseEntity<>("{\"message\":\"Region already exists\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @PatchMapping("/region/{id}")
    public ResponseEntity<String> updateRegion(@PathVariable int id, @RequestBody RegionDTO region) {
        RegionDTO updated = regionDAO.update(region, id);
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        ResponseEntity<String> result;
        if (updated != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(updated), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            result = new ResponseEntity<>("{\"message\":\"Region not found\"}", HttpStatus.OK);
        }
        return result;
    }

    @PutMapping("/region/{id}")
    public RegionDTO updateOrCreateRegion(@PathVariable int id, @RequestBody RegionDTO region) {
        return regionDAO.updateOrCreate(region, id);
    }

    @DeleteMapping("/region/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRegionById(@PathVariable int id) { regionDAO.delete(id); }

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
    public List<OrderDTO> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<String> getOrder(@PathVariable int id) {
        OrderDTO foundOrder = orderDAO.getByID(id);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundOrder != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(foundOrder), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Order not found\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @PostMapping("/order")
    public ResponseEntity<String> addNewOrder(@RequestBody Order newOrder) {
        OrderDTO savedOrder = orderDAO.addNewOrder(newOrder);
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

    @PatchMapping("/order")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
        OrderDTO updatedOrder = orderDAO.update(order);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (updatedOrder != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(updatedOrder), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Order could not be updated\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @DeleteMapping("/order/remove/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id) {
        int deletedOrderId = orderDAO.deleteOrder(id);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (deletedOrderId != -1) {
            result = new ResponseEntity<>(
                    "Order removed", headers,
                    HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Order could not be removed\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    // ---
    @GetMapping("/supplier/all")
    public List<SupplierDTO> getAllSuppliers(){
        return supplierDAO.getAllSuppliers();
    }

    @GetMapping("supplier/all/{country}")
    public List<SupplierDTO> getSuppliersByCountry(@PathVariable String country){
        return supplierDAO.getByCountry(country);
    }

    @PostMapping("/supplier")
    public ResponseEntity<String> addNewSupplier(@RequestBody Supplier newSupplier) {
        SupplierDTO savedSupplier = supplierDAO.addNewSupplier(newSupplier);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (savedSupplier != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(savedSupplier), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Supplier could not be added\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }

    @PatchMapping("/supplier")
    public ResponseEntity<String> updateSupplier(@RequestBody Supplier supplier) {
        SupplierDTO updatedSupplier = supplierDAO.update(supplier);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (updatedSupplier != null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(updatedSupplier), headers,
                        HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Supplier could not be updated\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }


    @DeleteMapping("/supplier/remove/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable(name = "id") int id) {
        int deletedSupplierId = supplierDAO.deleteSupplier(id);
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (deletedSupplierId != -1) {
            result = new ResponseEntity<>(
                    "Supplier removed", headers,
                    HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<>("{\"message\":\"Supplier could not be removed\"}",
                    headers, HttpStatus.OK);
        }
        return result;
    }
}
