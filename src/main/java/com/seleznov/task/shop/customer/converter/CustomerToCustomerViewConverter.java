package com.seleznov.task.shop.customer.converter;

import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.customer.model.ShippingAddress;
import com.seleznov.task.shop.customer.view.CustomerView;
import com.seleznov.task.shop.customer.view.ShippingAddressView;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author illcko
 */
public class CustomerToCustomerViewConverter implements Converter<Customer, CustomerView> {

    @Override
    public CustomerView convert(Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        CustomerView customerView = modelMapper.map(customer, CustomerView.class);
        Set<ShippingAddressView> shippingAddressViews = customer.getShippingAddresses().stream()
                .map(shippingAddress -> modelMapper.map(shippingAddress, ShippingAddressView.class))
                .collect(Collectors.toSet());
        customerView.setShippingAddressViews(shippingAddressViews);
        return customerView;
    }


}
