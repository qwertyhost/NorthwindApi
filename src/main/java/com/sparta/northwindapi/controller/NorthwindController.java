package com.sparta.northwindapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwindapi.dao.TerritoryDAO;
import com.sparta.northwindapi.dto.TerritoryDto;
import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Region;
import com.sparta.northwindapi.entity.Territory;
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

    @Autowired
    private TerritoryDAO territoryDAO;

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




    @GetMapping("/territory/all")
    public List<TerritoryDto> getAllTerritories() {
        return territoryDAO.getAllTerritories();
    }

    @PostMapping("/territory")
    public Territory newTerritory(String name, String id){

        Territory c = new Territory();
        c.setTerritoryDescription(name);
        c.setId(id);
        territoryRepository.save(c);
        return c;
    }



    @PutMapping("/territory/{id}/TerritoryDescription/{newTerritory}")
    public Territory updateTerritoryName(@PathVariable String id,@PathVariable String newTerritory){
        Territory t =territoryRepository.findById(id).get();
        t.setTerritoryDescription(newTerritory);
        territoryRepository.save(t);
        return t;

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
}
