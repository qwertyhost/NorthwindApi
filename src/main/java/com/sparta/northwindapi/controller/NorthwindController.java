package com.sparta.northwindapi.controller;

import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Region;
import com.sparta.northwindapi.entity.Territory;
import com.sparta.northwindapi.repo.EmployeeRepository;
import com.sparta.northwindapi.repo.EmployeeterritoryRepository;
import com.sparta.northwindapi.repo.RegionRepository;
import com.sparta.northwindapi.repo.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
