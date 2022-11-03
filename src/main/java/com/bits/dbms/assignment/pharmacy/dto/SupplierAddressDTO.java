package com.bits.dbms.assignment.pharmacy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierAddressDTO {
    private String supplier_name;
    private Long mobile_no;
    private String email_id;
    private AddressDTO address;
    private String created_by;
}
