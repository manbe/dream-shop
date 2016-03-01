package com.seleznov.task.shop.customer;

import com.seleznov.task.shop.customer.view.CustomerView;
import com.seleznov.task.shop.customer.view.ShippingAddressView;
import com.seleznov.task.shop.order.OrderService;
import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.order.view.OrderItemView;
import com.seleznov.task.shop.order.view.OrderView;
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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private OrderService orderService;


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

    @RequestMapping(value = "/{id}/order", method = RequestMethod.GET)
    public ResponseEntity<Collection<OrderView>> getCustomerOrderView(@PathVariable Long id) {
        Set<OrderView> orderViews = orderService.getAllOrders(id).stream()
                .map(order -> conversionService.convert(order, OrderView.class))
                .collect(Collectors.toSet());
        return new ResponseEntity<>(orderViews, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/order", method = RequestMethod.POST)
    public ResponseEntity makeOrder(@PathVariable Long id, @RequestBody @Valid OrderView orderView, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        ShopOrder shopOrder = conversionService.convert(orderView, ShopOrder.class);
        ShopOrder createdShopOrder = orderService.makeOrder(id, shopOrder);

        return new ResponseEntity(conversionService.convert(createdShopOrder, OrderView.class), HttpStatus.OK);
    }



}
