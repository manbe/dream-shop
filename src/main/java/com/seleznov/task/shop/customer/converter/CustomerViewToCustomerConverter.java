package com.seleznov.task.shop.customer.converter;

import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.customer.model.ShippingAddress;
import com.seleznov.task.shop.customer.view.CustomerView;
import com.seleznov.task.shop.customer.view.ShippingAddressView;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author illcko
 */
public class CustomerViewToCustomerConverter implements Converter<CustomerView, Customer> {
    public static final ModelMapper modelMapper = new ModelMapper();

    public static Function<ShippingAddressView, ShippingAddress> shippingAddressViewShippingAddressFunction(Customer customer)  {
        return shippingAddressView -> {
            ShippingAddress shippingAddress = modelMapper.map(shippingAddressView, ShippingAddress.class);
            shippingAddress.setCustomer(customer);
            return shippingAddress;
        };
    };

    @Override
    public Customer convert(CustomerView customerView) {
        Customer customer = modelMapper.map(customerView, Customer.class);
        Collection<ShippingAddress> shippingAddresses = customerView.getShippingAddressViews().stream()
                .map(shippingAddressViewShippingAddressFunction(customer))
                .collect(Collectors.toCollection(HashSet::new));
        customer.setShippingAddresses(shippingAddresses);
        return customer;
    }
}
