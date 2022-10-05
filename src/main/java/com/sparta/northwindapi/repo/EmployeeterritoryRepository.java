package com.sparta.northwindapi.repo;

import com.sparta.northwindapi.entity.Employeeterritory;
import com.sparta.northwindapi.entity.EmployeeterritoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeterritoryRepository extends JpaRepository<Employeeterritory, EmployeeterritoryId> {
}