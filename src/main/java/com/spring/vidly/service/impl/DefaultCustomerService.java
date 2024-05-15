package com.spring.vidly.service.impl;

import com.spring.vidly.dto.CustomerDTO;
import com.spring.vidly.exception.CustomerNotFoundException;
import com.spring.vidly.reposity.CustomerRepository;
import com.spring.vidly.service.CustomerService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DefaultCustomerService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getAll() {
        var customers = customerRepository.findAll();
        return customers.stream().map(CustomerDTO::createFromCustomer).toList();
    }

    @Override
    public CustomerDTO getCustomer(String uuid) throws CustomerNotFoundException {
        var customer = customerRepository.findCustomerByUuid(uuid);
        if (Objects.isNull(customer)) throw new CustomerNotFoundException(uuid);

        return CustomerDTO.createFromCustomer(customer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        var customer = customerDTO.convertDto();
        customerRepository.save(customer);
        return CustomerDTO.createFromCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(String uuid, CustomerDTO customerDTO) throws CustomerNotFoundException {
        var customer = customerRepository.findCustomerByUuid(uuid);
        if (Objects.isNull(customer)) {
            throw new CustomerNotFoundException(uuid);
        }

        customer.setIsGold(!Objects.isNull(customerDTO.isGold()) ? customerDTO.isGold() : customer.getIsGold());
        customer.setName(!Objects.isNull(customerDTO.name()) ? customerDTO.name() : customer.getName());
        customer.setPhone(!Objects.isNull(customerDTO.phone()) ? customerDTO.phone() : customer.getPhone());

        customerRepository.save(customer);

        return CustomerDTO.createFromCustomer(customer);
    }

    @Override
    public void deleteCustomer(String uuid) {
        customerRepository.deleteCustomerByUuid(uuid);
    }
}
