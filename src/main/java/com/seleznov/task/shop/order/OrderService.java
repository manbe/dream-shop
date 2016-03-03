package com.seleznov.task.shop.order;

import com.seleznov.task.shop.order.model.ShopOrder;

import java.util.Collection;

/**
 * @author illcko
 */
public interface OrderService {

    Collection<ShopOrder> getOrdersForCurrentCustomer(Long customerId);

    ShopOrder makeOrder(Long customerId, ShopOrder shopOrder);

}
