package com.seleznov.task.shop.customer;

import com.seleznov.task.shop.customer.view.CustomerView;
import com.seleznov.task.shop.customer.view.ShippingAddressView;
import com.seleznov.task.shop.order.OrderService;
import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.order.view.OrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author illcko
 */
@RestController
@RequestMapping("shop/api/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ConversionService conversionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<CustomerView>> getCustomers() {
        Set<CustomerView> customers = customerService.getAllCustomers().stream()
                .map(customer -> conversionService.convert(customer, CustomerView.class))
                .collect(Collectors.toSet());
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerView> getCustomer(@PathVariable Long id) {
        CustomerView customerView = conversionService.convert(customerService.getCustomer(id), CustomerView.class);
        return new ResponseEntity<>(customerView, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/shippingAddress", method = RequestMethod.GET)
    public ResponseEntity<Collection<ShippingAddressView>> getShippingAdresses(@PathVariable Long id) {
        Set<ShippingAddressView> shippingAddressViews = customerService.getCustomer(id).getShippingAddresses().stream()
                .map(shippingAddress -> conversionService.convert(shippingAddress, ShippingAddressView.class))
                .collect(Collectors.toSet());
        return new ResponseEntity<>(shippingAddressViews, HttpStatus.OK);
    }





}
