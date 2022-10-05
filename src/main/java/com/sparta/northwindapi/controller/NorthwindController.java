package com.sparta.northwindapi.controller;

import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Employeeterritory;
import com.sparta.northwindapi.entity.Territory;
import com.sparta.northwindapi.repo.EmployeeRepository;
import com.sparta.northwindapi.repo.EmployeeterritoryRepository;
import com.sparta.northwindapi.repo.RegionRepository;
import com.sparta.northwindapi.repo.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NorthwindController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private EmployeeterritoryRepository employeeterritoryRepo;

    @Autowired
    private RegionRepository regionRepo;

    @Autowired
    private TerritoryRepository territoryRepo;

    @GetMapping("/Employee")

    @GetMapping


}
