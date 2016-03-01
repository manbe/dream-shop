package com.seleznov.task.shop.customer;

import com.seleznov.task.shop.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author illcko
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
