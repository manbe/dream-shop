package com.seleznov.task.shop.customer.converter;

import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.customer.model.ShippingAddress;
import com.seleznov.task.shop.customer.view.CustomerView;
import com.seleznov.task.shop.customer.view.ShippingAddressView;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * @author illcko
 */
public class CustomerViewToCustomerConverterTest {

    public static final Long CUSTOMER_ID = 5l;
    public static final String MAIL = "lol@mail.com";
    public static final Integer BALANCE = 10;
    public static final String CUSTOMER_NAME = "lol";
    public static final int FLAT = 4;
    public static final Long SHIPPING_ADDRESS_ID = 7l;
    public static final String HOUSE = "Of ricing sun";
    public static final String POSTAL_CODE = "postal";
    public static final String STREET = "street";

    @Test
    public void testConvert() throws Exception {
        CustomerView customerView = createCustomerView();

        CustomerViewToCustomerConverter customerToCustomerViewConverter = new CustomerViewToCustomerConverter();
        Customer customer = customerToCustomerViewConverter.convert(customerView);

        assertEquals(CUSTOMER_ID, customer.getId());
        assertEquals(BALANCE, customer.getBalance());
        assertEquals(MAIL, customer.getEmail());
        assertEquals(CUSTOMER_NAME, customer.getName());

        ShippingAddress shippingAddress = customer.getShippingAddresses().iterator().next();
        assertEquals(shippingAddress.getId(), SHIPPING_ADDRESS_ID);
        assertEquals(shippingAddress.getFlat(), FLAT);
        assertEquals(shippingAddress.getHouse(), HOUSE);
        assertEquals(shippingAddress.getPostalCode(), POSTAL_CODE);
        assertEquals(shippingAddress.getStreet(), STREET);
        assertEquals(shippingAddress.getCustomer(), customer);
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