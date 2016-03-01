package com.seleznov.task.shop.customer.converter;

import com.seleznov.task.shop.customer.model.ShippingAddress;
import com.seleznov.task.shop.customer.view.ShippingAddressView;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

/**
 * @author illcko
 */
public class ShippingAddressToShippingAddressViewConverter implements Converter<ShippingAddress, ShippingAddressView> {

    @Override
    public ShippingAddressView convert(ShippingAddress shippingAddress) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(shippingAddress, ShippingAddressView.class);
    }
}
