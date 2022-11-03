package com.bits.dbms.assignment.pharmacy.service;

import com.bits.dbms.assignment.pharmacy.dto.AddressDTO;
import com.bits.dbms.assignment.pharmacy.dto.SupplierAddressDTO;
import com.bits.dbms.assignment.pharmacy.entity.Address;
import com.bits.dbms.assignment.pharmacy.entity.Supplier;
import com.bits.dbms.assignment.pharmacy.repository.AddressRepository;
import com.bits.dbms.assignment.pharmacy.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final AddressRepository addressRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository, AddressRepository addressRepository) {
        this.supplierRepository = supplierRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findSupplierById(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElse(null);
    }

    @Override
    @Transactional
    public Supplier saveSupplier(SupplierAddressDTO supplierDTO) {
        AddressDTO addressDTO = supplierDTO.getAddress();
        Address supplierAddress = Address.builder()
                .addressLine1(addressDTO.getAddressLine1())
                .addressLine2(addressDTO.getAddressLine2())
                .addressLine3(addressDTO.getAddressLine3())
                .state(addressDTO.getState())
                .city(addressDTO.getCity())
                .country(addressDTO.getCountry())
                .zip(addressDTO.getZip())
                .created_by(supplierDTO.getCreated_by())
                .build();

        Address savedAddress = addressRepository.save(supplierAddress);

        Supplier supplier = Supplier.builder()
                .supplier_name(supplierDTO.getSupplier_name())
                .address_id(savedAddress.getAddress_id())
                .mobile_no(supplierDTO.getMobile_no())
                .email_id(supplierDTO.getEmail_id())
                .created_by(supplierDTO.getCreated_by())
                .build();

        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier, Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier supplierDB = optionalSupplier.get();
            if (Objects.nonNull(supplier.getSupplier_name())) {
                supplierDB.setSupplier_name(supplier.getSupplier_name());
            }
            if (Objects.nonNull(supplier.getAddress_id())) {
                supplierDB.setAddress_id(supplier.getAddress_id());
            }
            if (Objects.nonNull(supplier.getMobile_no())) {
                supplierDB.setMobile_no(supplier.getMobile_no());
            }
            if (Objects.nonNull(supplier.getEmail_id())) {
                supplierDB.setEmail_id(supplier.getEmail_id());
            }
            supplierDB.setModified_on(new Date());
            supplierRepository.save(supplierDB);
            return supplierDB;
        } else {
            return supplierRepository.save(supplier);
        }
    }

    @Override
    public void deleteSupplierById(Integer id) {
        supplierRepository.deleteById(id);
    }

}
