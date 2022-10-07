package com.sparta.northwindapi.dao;

import com.sparta.northwindapi.dto.CustomerDto;
import com.sparta.northwindapi.entity.Customer;
import com.sparta.northwindapi.repo.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDAO {
    CustomerRepository customerRepo;

    public CustomerDAO(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }


    public CustomerDto update(CustomerDto customerDto){
        Optional<Customer> optional = customerRepo.findById(customerDto.getId());
        Customer customer = null;
        if(optional.isPresent()){
            customer = optional.get();
        }else {
            return new CustomerDto(null,null,null,null,null,null,null,null,null,null,null);
        }

        if(customerDto.getId() !=null){
            customer.setId(customerDto.getId());
        }
        if(customerDto.getCompanyName() !=null){
            customer.setCompanyName(customerDto.getCompanyName());
        }
        if(customerDto.getContactName() !=null){
            customer.setContactName(customerDto.getContactName());
        }
        if(customerDto.getContactTitle() !=null){
            customer.setContactTitle(customerDto.getContactTitle());
        }
        if(customerDto.getAddress() !=null){
            customer.setAddress(customerDto.getAddress());
        }
        if(customerDto.getCity() !=null){
            customer.setCity(customerDto.getCity());
        }
        if(customerDto.getRegion() !=null){
            customer.setRegion(customerDto.getRegion());
        }
        if(customerDto.getPostalCode() !=null){
            customer.setPostalCode(customerDto.getPostalCode());
        }
        if(customerDto.getCountry() !=null){
            customer.setCountry(customerDto.getCountry());
        }
        if(customerDto.getPhone() !=null){
            customer.setPhone(customerDto.getPhone());
        }
        if(customerDto.getFax() !=null){
            customer.setFax(customerDto.getFax());
        }
        customerRepo.save(customer);
        customer = customerRepo.findById(customerDto.getId()).get();
        return
                new CustomerDto(customer.getId(),customer.getCompanyName(),customer.getContactName()
                ,customer.getContactTitle(),customer.getAddress(),customer.getCity(),
                customer.getRegion(),customer.getPostalCode(),customer.getCountry(),
                customer.getPhone(),customer.getFax());
    }

    public void delete(String id){
        customerRepo.deleteById(id);
    }

    public boolean create(CustomerDto customerDto){
        Optional<Customer> optional = customerRepo.findById(customerDto.getId());
        Customer customer;
        if(optional.isPresent()){
            customer = optional.get();
        }else {
            return false;
        }
        customer.setId(customerDto.getId());
        customer.setCompanyName(customerDto.getCompanyName());
        customer.setContactName(customerDto.getContactName());
        customer.setContactTitle(customerDto.getContactTitle());
        customer.setAddress(customerDto.getAddress());
        customer.setCity(customerDto.getCity());
        customer.setRegion(customerDto.getRegion());
        customer.setPostalCode(customerDto.getPostalCode());
        customer.setCountry(customerDto.getCountry());
        customer.setPhone(customerDto.getPhone());
        customer.setFax(customerDto.getFax());
        customerRepo.save(customer);
        return true;
    }

    public Customer read(String id){
        return customerRepo.findById(id).get();
    }

    public List<Customer> readAll(){
        return customerRepo.findAll();
    }
}
