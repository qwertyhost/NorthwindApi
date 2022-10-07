package com.sparta.northwindapi.repo;

import com.sparta.northwindapi.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findByCountry(String country);
}