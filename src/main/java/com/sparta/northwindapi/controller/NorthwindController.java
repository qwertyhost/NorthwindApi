package com.sparta.northwindapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwindapi.dao.OrderDAO;
import com.sparta.northwindapi.dto.OrderDTO;
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

import java.util.List;
import java.util.Optional;

@RestController
public class NorthwindController {

    private final EmployeeRepository employeeRepository;
    private final TerritoryRepository territoryRepository;
    private final RegionRepository regionRepository;

    private final OrderDAO orderDAO;

    private ObjectMapper mapper;
    private HttpHeaders headers;

    public NorthwindController(EmployeeRepository employeeRepository, TerritoryRepository territoryRepository, RegionRepository regionRepository, OrderDAO orderDAO) {
        this.employeeRepository = employeeRepository;
        this.territoryRepository = territoryRepository;
        this.regionRepository = regionRepository;
        this.orderDAO = orderDAO;
        mapper = new ObjectMapper();
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
}
