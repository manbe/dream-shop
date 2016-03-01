package com.seleznov.task.shop.customer.converter;

import com.seleznov.task.shop.customer.model.ShippingAddress;

/**
 * @author illcko
 */
public class CustomerConverterTestHelper {

    public static final Long ID = 1l;
    public static final int FLAT_NUMBER = 5;
    public static final String HOUSE_NUMBER = "2B";
    public static final String POSTAL_CODE = "12345";

    public static ShippingAddress createShippingAddress(){
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(ID);
        shippingAddress.setFlat(FLAT_NUMBER);
        shippingAddress.setHouse(HOUSE_NUMBER);
        shippingAddress.setPostalCode(POSTAL_CODE);
        return shippingAddress;
    }
}
