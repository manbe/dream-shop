package com.seleznov.task.shop.order;

import com.seleznov.task.shop.order.model.ShopOrder;
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
public class OrderController {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/{id}/order", method = RequestMethod.GET)
    public ResponseEntity<Collection<OrderView>> getCustomerOrderView(@PathVariable Long id) {
        Set<OrderView> orderViews = orderService.getOrdersForCurrentCustomer(id).stream()
                .map(order -> conversionService.convert(order, OrderView.class))
                .collect(Collectors.toSet());
        return new ResponseEntity<>(orderViews, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/order", method = RequestMethod.POST)
    public ResponseEntity<OrderView> makeOrder(@PathVariable Long id, @Valid @RequestBody OrderView orderView) {
        ShopOrder shopOrder = conversionService.convert(orderView, ShopOrder.class);
        ShopOrder createdShopOrder = orderService.makeOrder(id, shopOrder);

        return new ResponseEntity(conversionService.convert(createdShopOrder, OrderView.class), HttpStatus.OK);
    }
}
