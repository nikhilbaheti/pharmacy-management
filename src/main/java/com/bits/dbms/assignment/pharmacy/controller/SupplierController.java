package com.bits.dbms.assignment.pharmacy.controller;

import com.bits.dbms.assignment.pharmacy.dto.SupplierAddressDTO;
import com.bits.dbms.assignment.pharmacy.entity.Supplier;
import com.bits.dbms.assignment.pharmacy.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> findAllSuppliers() {
        return supplierService.findAllSuppliers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findSupplierById(@PathVariable(value = "id") Integer id) {
        Supplier supplier = supplierService.findSupplierById(id);
        if (supplier != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(supplier);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public Supplier saveSupplier(@Validated @RequestBody SupplierAddressDTO supplier) {
        return supplierService.saveSupplier(supplier);
    }

    @PutMapping
    public Supplier updateSupplier(@Validated @RequestBody Supplier supplier, Integer id) {
        return supplierService.updateSupplier(supplier, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplierById(@PathVariable(value = "id") Integer id) {
        supplierService.deleteSupplierById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Deleted Successfully");
    }
}
