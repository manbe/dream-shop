package com.seleznov.task.shop.customer;

import com.seleznov.task.shop.customer.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author illcko
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    public static final long ID = 1l;
    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @Before
    public void init(){
        customer = new Customer();
        customer.setId(ID);

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
        when(customerRepository.findOne(ID)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);

    }

    @Test
    public void testGetAllCustomers() throws Exception {
        Collection<Customer> allCustomers = customerService.getAllCustomers();
        assertEquals(1, allCustomers.size());
        assertEquals(customer, allCustomers.iterator().next());
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer resultCustomer = customerService.updateCustomer(customer);
        assertEquals(customer,resultCustomer);
    }

    @Test
    public void testGetCustomer() throws Exception {
        Customer resultCustomer = customerService.getCustomer(ID);
        assertEquals(customer,resultCustomer);
    }
}