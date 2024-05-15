package com.spring.vidly.controller;

import com.spring.vidly.dto.CustomerDTO;
import com.spring.vidly.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/")
    public List<CustomerDTO> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{uuid}")
    public CustomerDTO getCustomer(@PathVariable(name = "uuid") String uuid) {
        return customerService.getCustomer(uuid);
    }

    @PostMapping("/")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @PutMapping("/{uuid}")
    public CustomerDTO updateCustomer(@PathVariable(name = "uuid") String uuid, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(uuid, customerDTO);
    }

    @DeleteMapping("/{uuid}")
    public void deleteCustomer(@PathVariable String uuid) {
        customerService.deleteCustomer(uuid);
    }
}
