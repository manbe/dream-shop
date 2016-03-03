package com.seleznov.task.shop.order.converter;

import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.order.model.OrderItem;
import com.seleznov.task.shop.order.view.OrderItemView;
import com.seleznov.task.shop.order.view.OrderView;
import com.seleznov.task.shop.sku.StockKeepingUnit;
import com.seleznov.task.shop.sku.StockKeepingUnitView;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author illcko
 */
public class OrderViewToShopOrderConverterTest {
    public static final Long ID = 222l;

    public static final Integer AMOUNT = 5;
    public static final Integer ORDER_AMOUNT = 3;
    public static final String NAME = "name";
    public static final Integer PRICE = 10;

    @Test
    public void testConvert() throws Exception {
        OrderView orderView= new OrderView();
        orderView.setId(ID);
        orderView.setTotalPrice(PRICE);


        OrderItemView orderItemView = new OrderItemView();
        orderItemView.setId(ID);
        orderItemView.setAmount(ORDER_AMOUNT);

        orderItemView.setStockKeepingUnitId(ID);

        orderView.setOrderItemViews(Collections.singletonList(orderItemView));

        OrderViewToOrderConverter orderViewToOrderConverter = new OrderViewToOrderConverter();

        ShopOrder shopOrder = orderViewToOrderConverter.convert(orderView);

        assertNull(shopOrder.getId());

        OrderItem orderItem = shopOrder.getOrderItems().iterator().next();

        assertNull(orderItem.getId());
        assertEquals(ORDER_AMOUNT, orderItem.getAmount());

        StockKeepingUnit stockKeepingUnit = orderItem.getStockKeepingUnit();
        assertEquals(ID, stockKeepingUnit.getId());

    }
}