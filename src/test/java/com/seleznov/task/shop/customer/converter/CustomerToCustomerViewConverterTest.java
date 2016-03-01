package com.seleznov.task.shop.customer.converter;

import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.customer.model.ShippingAddress;
import com.seleznov.task.shop.customer.view.CustomerView;
import com.seleznov.task.shop.customer.view.ShippingAddressView;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static com.seleznov.task.shop.customer.converter.CustomerConverterTestHelper.*;

/**
 * @author illcko
 */
public class CustomerToCustomerViewConverterTest {
    public static final String EMAIL = "email@lol.com";
    public static final String NAME = "NAME";
    public static final Integer BALANCE = 100;

    @Test
    public void testConvert() throws Exception {

        ShippingAddress shippingAddress = CustomerConverterTestHelper.createShippingAddress();

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setEmail(EMAIL);
        customer.setName(NAME);
        customer.setShippingAddresses(Arrays.asList(shippingAddress));
        customer.setVersion(123);
        customer.setBalance(BALANCE);

        CustomerToCustomerViewConverter customerToCustomerViewConverter = new CustomerToCustomerViewConverter();
        CustomerView customerView = customerToCustomerViewConverter.convert(customer);
        assertEquals(NAME, customerView.getName());
        assertEquals(EMAIL, customerView.getEmail());
        assertEquals(ID, customerView.getId());
        assertEquals(BALANCE, customerView.getBalance());

        ShippingAddressView shippingAddressView = customerView.getShippingAddressViews().iterator().next();

        assertEquals(FLAT_NUMBER, shippingAddressView.getFlat());
        assertEquals(HOUSE_NUMBER, shippingAddressView.getHouse());
        assertEquals(POSTAL_CODE, shippingAddressView.getPostalCode());


    }
}