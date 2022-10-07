package com.sparta.northwindapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sparta.northwindapi.dao.CustomerDAO;
import com.sparta.northwindapi.dao.TerritoryDAO;
import com.sparta.northwindapi.dto.CustomerDto;
import com.sparta.northwindapi.dto.TerritoryDto;
import com.sparta.northwindapi.entity.Customer;
import com.sparta.northwindapi.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.sparta.northwindapi.dao.OrderDAO;
import com.sparta.northwindapi.dao.SupplierDAO;
import com.sparta.northwindapi.dto.OrderDTO;
import com.sparta.northwindapi.dto.SupplierDTO;
import com.sparta.northwindapi.entity.*;
import com.sparta.northwindapi.repo.EmployeeRepository;
import com.sparta.northwindapi.repo.RegionRepository;
import com.sparta.northwindapi.repo.TerritoryRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class NorthwindController {


    @Autowired
    private CustomerRepository customerRepo;
        
    private final EmployeeRepository employeeRepository;
    private final TerritoryRepository territoryRepository;
    private final RegionRepository regionRepository;

    private final OrderDAO orderDAO;
    private final SupplierDAO supplierDAO;
    private TerritoryDAO territoryDAO;

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

    @GetMapping("/order/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @GetMapping("/order/{id}")
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.CREATED)
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
    @ResponseStatus(HttpStatus.CREATED)
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
    @ResponseStatus(HttpStatus.OK)
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


    @GetMapping("/territory/all")
    public List<TerritoryDto> getAllTerritories() {
        return territoryDAO.getAllTerritories();
    }

    @GetMapping("/territory/{id}")
    public ResponseEntity<String> getTerritory(@PathVariable String id) {
        TerritoryDto foundTerritory = territoryDAO.getTerritoryById(id);
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.add("content-type","application/json");
        ResponseEntity<String> result = null;
        if (foundTerritory!= null) {
            try {
                result = new ResponseEntity<>(
                        mapper.writeValueAsString(foundTerritory), headers,
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

    @PutMapping("/territory/{id}/TerritoryDescription/{newTerritory}")
    public Territory updateTerritoryName(@PathVariable String id,@PathVariable String newTerritory){
        Territory t =territoryRepository.findById(id).get();
        t.setTerritoryDescription(newTerritory);
        territoryRepository.save(t);
        return t;

    }

    @PostMapping("/territory")
    public Territory newTerritory(String name, String id){

        Territory c = new Territory();
        c.setTerritoryDescription(name);
        c.setId(id);
        territoryRepository.save(c);
        return c;
    }


}
