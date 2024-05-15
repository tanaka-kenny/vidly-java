package com.spring.vidly.service;

import com.spring.vidly.dto.CustomerDTO;
import com.spring.vidly.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAll();

    CustomerDTO getCustomer(String uuid) throws CustomerNotFoundException;

    CustomerDTO createCustomer(CustomerDTO customer);

    CustomerDTO updateCustomer(String uuid, CustomerDTO customerDTO) throws CustomerNotFoundException;

    void deleteCustomer(String uuid);
}
