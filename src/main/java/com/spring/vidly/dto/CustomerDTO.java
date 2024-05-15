package com.spring.vidly.dto;

import com.spring.vidly.domain.Customer;
import org.apache.commons.lang3.ObjectUtils;

import java.util.UUID;

public record CustomerDTO(
    String uuid,
    Boolean isGold,
    String name,
    String phone) {

    public static CustomerDTO createFromCustomer(Customer customer) {
        return new CustomerDTO(customer.getUuid(), customer.getIsGold(), customer.getName(), customer.getPhone());
    }

    public Customer convertDto() {
        var customer = new Customer();
        customer.setUuid(ObjectUtils.isNotEmpty(uuid()) ? uuid() : UUID.randomUUID().toString());
        customer.setIsGold(isGold());
        customer.setName(name());
        customer.setPhone(phone());

        return customer;
    }
}
