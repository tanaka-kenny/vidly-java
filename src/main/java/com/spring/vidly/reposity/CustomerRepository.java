package com.spring.vidly.reposity;

import com.spring.vidly.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByUuid(String uuid);

    void deleteCustomerByUuid(String uuid);
}
