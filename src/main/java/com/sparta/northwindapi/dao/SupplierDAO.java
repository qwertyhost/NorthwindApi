package com.sparta.northwindapi.dao;

import com.sparta.northwindapi.dto.SupplierDTO;
import com.sparta.northwindapi.entity.Supplier;
import com.sparta.northwindapi.repo.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierDAO {

    private final SupplierRepository supplierRepository;


    public SupplierDAO(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    //READ
    public List<SupplierDTO> getAllSuppliers() {
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        List<Supplier> suppliers = supplierRepository.findAll();
        for (int i = 0; i < suppliers.size(); i++) {
            supplierDTOList.add(convertSupplier(suppliers.get(i)));
        }
        return supplierDTOList;
    }

    public SupplierDTO getByID(int id){
        Optional<Supplier> foundSupplier = supplierRepository.findById(id);
        if (foundSupplier.isPresent())
            return convertSupplier(foundSupplier.get());
        else
            return new SupplierDTO(-1, null, null, null, null, null, null, null, null, null, null, null);
    }

    //READ
    public List<SupplierDTO> getByCountry(String country){
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        List<Supplier> suppliers = supplierRepository.findByCountry(country);
        for (int i = 0; i < suppliers.size(); i++)
            supplierDTOList.add(convertSupplier(suppliers.get(i)));
        return supplierDTOList;
    }

    //UPDATE
    public SupplierDTO update(Supplier supplier){
        Optional<Supplier> optional = supplierRepository.findById(supplier.getId());
        Supplier updatedSupplier = null;
        if(optional.isPresent())
            updatedSupplier = optional.get();
        else
            return new SupplierDTO(-1, null, null, null, null, null, null, null, null, null, null, null);

        if(supplier.getCompanyName() != null)
            updatedSupplier.setCompanyName(supplier.getCompanyName());
        if(supplier.getContactName() != null)
            updatedSupplier.setContactName(supplier.getContactName());
        if(supplier.getContactTitle() != null)
            updatedSupplier.setContactTitle(supplier.getContactTitle());
        if(supplier.getAddress() != null)
            updatedSupplier.setAddress(supplier.getAddress());
        if(supplier.getCity() != null)
            updatedSupplier.setCity(supplier.getCity());
        if(supplier.getRegion() != null)
            updatedSupplier.setRegion(supplier.getRegion());
        if(supplier.getPostalCode() != null)
            updatedSupplier.setPostalCode(supplier.getPostalCode());
        if(supplier.getCountry() != null)
            updatedSupplier.setCountry(supplier.getCountry());
        if(supplier.getPhone() != null)
            updatedSupplier.setPhone(supplier.getPhone());
        if(supplier.getFax() != null)
            updatedSupplier.setFax(supplier.getFax());
        if(supplier.getHomePage() != null)
            updatedSupplier.setHomePage(supplier.getHomePage());

        supplierRepository.save(updatedSupplier);
        updatedSupplier = supplierRepository.findById(supplier.getId()).get();
        return new SupplierDTO(supplier.getId(), supplier.getCompanyName(), supplier.getContactName(), supplier.getContactTitle(),
                supplier.getAddress(), supplier.getCity(), supplier.getRegion(), supplier.getPostalCode(), supplier.getCountry(), supplier.getPhone(),
                supplier.getFax(), supplier.getHomePage());
    }

    //CREATE
    public SupplierDTO addNewSupplier(Supplier newSupplier) {
        Supplier savedSupplier = supplierRepository.save(newSupplier);
        if(savedSupplier != null)
            return convertSupplier(savedSupplier);
        else
            return new SupplierDTO(-1, null, null, null, null, null, null, null, null, null, null, null);
    }

    public int deleteSupplier(int id) {
        Optional<Supplier> foundSupplier = supplierRepository.findById(id);
        if (foundSupplier.isPresent()) {
            supplierRepository.delete(foundSupplier.get());
            return foundSupplier.get().getId();
        }
        else {
            return -1;
        }
    }


    public SupplierDTO convertSupplier(Supplier supplier) {
        SupplierDTO converted = new SupplierDTO(supplier.getId(), supplier.getCompanyName(), supplier.getContactName(), supplier.getContactTitle(),
                supplier.getAddress(), supplier.getCity(), supplier.getRegion(), supplier.getPostalCode(), supplier.getCountry(), supplier.getPhone(),
                supplier.getFax(), supplier.getHomePage());
        return converted;
    }
}
