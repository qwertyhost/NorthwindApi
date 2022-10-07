package com.sparta.northwindapi.repo;

import com.sparta.northwindapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}