package com.seleznov.task.shop.customer.converter;

import com.seleznov.task.shop.customer.model.ShippingAddress;
import com.seleznov.task.shop.customer.view.ShippingAddressView;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.seleznov.task.shop.customer.converter.CustomerConverterTestHelper.*;

/**
 * @author illcko
 */
public class ShippingAddressToShippingAddressViewConverterTest {

    @Test
    public void testConvert() throws Exception {
        ShippingAddress shippingAddress = createShippingAddress();
        ShippingAddressToShippingAddressViewConverter shippingAddressToShippingAddressViewConverter = new ShippingAddressToShippingAddressViewConverter();
        ShippingAddressView shippingAddressView = shippingAddressToShippingAddressViewConverter.convert(shippingAddress);

        assertEquals(FLAT_NUMBER, shippingAddressView.getFlat());
        assertEquals(HOUSE_NUMBER, shippingAddressView.getHouse());
        assertEquals(POSTAL_CODE, shippingAddressView.getPostalCode());
    }
}