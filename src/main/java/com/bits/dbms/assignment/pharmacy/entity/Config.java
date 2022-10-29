package com.bits.dbms.assignment.pharmacy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "config")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer billing_id;
    private Integer customer_id;
    private String store_id;
    private String offer_id;
    private Integer discount_amount;
    private Integer net_amount;
    private String payment_mode;
    private Date billing_date;
    private String created_by;
    private Date created_on;
    private String modified_by;
    private Date modified_on;
}









