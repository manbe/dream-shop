package com.seleznov.task.shop.order;

import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.order.model.OrderItem;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * @author illcko
 */
public class TotalPriceCalculationUtilTest {

    @Test
    public void testCalculateTotalPrice() throws Exception {
        ShopOrder shopOrder = new ShopOrder();
        HashSet<OrderItem> orderItems = new HashSet<>();

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setActualPrice(1);
        orderItem2.setAmount(2);
        orderItems.add(orderItem2);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setActualPrice(3);
        orderItem1.setAmount(1);
        orderItems.add(orderItem1);

        shopOrder.setOrderItems(orderItems);

        int totalPrice = TotalPriceCalculationUtil.calculateTotalPrice(shopOrder);
        assertEquals(5, totalPrice);
    }
}