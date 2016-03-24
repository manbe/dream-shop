package com.seleznov.task.shop.customer;

import com.seleznov.task.shop.customer.model.Customer;

import java.util.Collection;

/**
 * @author illcko
 */
public interface CustomerService {

    Collection<Customer> getAllCustomers();

    Customer updateCustomer(Customer customer);

    Customer getCustomer(Long id);

    Customer createCustomer(Customer customer);

}
