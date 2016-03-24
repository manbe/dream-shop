package com.seleznov.task.shop.customer;

import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.exception.IllegalCustomerForCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;

/**
 * @author illcko
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Collection<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if(!Objects.isNull(customer.getId())){
            throw new IllegalCustomerForCreateException("Customer should not have an id");
        };
        return customerRepository.save(customer);
    }
}
