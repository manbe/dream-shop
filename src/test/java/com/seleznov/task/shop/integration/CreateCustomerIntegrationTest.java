package com.seleznov.task.shop.integration;

import com.seleznov.task.shop.customer.CustomerRepository;
import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.customer.view.CustomerView;
import com.seleznov.task.shop.customer.view.ShippingAddressView;
import com.seleznov.task.shop.exception.ErrorConstants;
import com.seleznov.task.shop.exception.view.ErrorView;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author illcko
 */
public class CreateCustomerIntegrationTest extends BaseIntegrationTest {

    public static final Long CUSTOMER_ID = 1l;
    public static final String MAIL = "lol@mail.com";
    public static final Integer BALANCE = 10;
    public static final String CUSTOMER_NAME = "lol";
    public static final int FLAT = 4;
    public static final Long SHIPPING_ADDRESS_ID = 7l;
    public static final String HOUSE = "12";
    public static final String POSTAL_CODE = "66666";
    public static final String STREET = "street";

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void createCustomerTest() {
        CustomerView customerView = createCustomerView();

        //Check that we can't update existing customer
        ResponseEntity<ErrorView> errorViewResponseEntity = restTemplate.postForEntity(BASE_URL + CUSTOMER, customerView, ErrorView.class);
        assertEquals(HttpStatus.BAD_REQUEST, errorViewResponseEntity.getStatusCode());
        assertEquals("Customer should not have an id", errorViewResponseEntity.getBody().getMessage());
        assertEquals(ErrorConstants.ERR_OPERATION_DENIED, errorViewResponseEntity.getBody().getCode());

        //Create new Customer
        customerView.setId(null);
        ResponseEntity<CustomerView> customerViewResponseEntity = restTemplate.postForEntity(BASE_URL + CUSTOMER, customerView, CustomerView.class);
        customerView = customerViewResponseEntity.getBody();
        assertNotNull(customerView.getId());
    }


    private CustomerView createCustomerView() {
        CustomerView customerView = new CustomerView();
        customerView.setBalance(BALANCE);
        customerView.setEmail(MAIL);
        customerView.setName(CUSTOMER_NAME);
        customerView.setId(CUSTOMER_ID);

        ShippingAddressView shippingAddressView = createShippingAddressView();
        customerView.setShippingAddressViews(Collections.singleton(shippingAddressView));
        return customerView;
    }

    private ShippingAddressView createShippingAddressView() {
        ShippingAddressView shippingAddressView = new ShippingAddressView();

        shippingAddressView.setFlat(FLAT);
        shippingAddressView.setId(SHIPPING_ADDRESS_ID);
        shippingAddressView.setHouse(HOUSE);
        shippingAddressView.setPostalCode(POSTAL_CODE);
        shippingAddressView.setStreet(STREET);

        return shippingAddressView;
    }

}
