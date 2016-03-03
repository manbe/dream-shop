package com.seleznov.task.shop.order.converter;

import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.order.model.OrderItem;
import com.seleznov.task.shop.order.view.OrderItemView;
import com.seleznov.task.shop.order.view.OrderView;
import com.seleznov.task.shop.sku.StockKeepingUnit;
import com.seleznov.task.shop.sku.StockKeepingUnitView;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * @author illcko
 */
public class OrderToShopOrderViewConverterTest {

    public static final Long ID = 222l;

    public static final Integer AMOUNT = 5;
    public static final String NAME = "name";
    public static final Integer PRICE = 10;
    public static final Integer ORDER_AMOUNT = 3;

    @Test
    public void testConvert() throws Exception {

        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setId(ID);

        StockKeepingUnit stockKeepingUnit = new StockKeepingUnit();
        stockKeepingUnit.setId(ID);
        stockKeepingUnit.setAmount(AMOUNT);
        stockKeepingUnit.setName(NAME);
        stockKeepingUnit.setPrice(PRICE);

        OrderItem orderItem = new OrderItem();
        orderItem.setActualPrice(PRICE);
        orderItem.setId(ID);
        orderItem.setAmount(ORDER_AMOUNT);
        orderItem.setStockKeepingUnit(stockKeepingUnit);

        shopOrder.setOrderItems(Collections.singletonList(orderItem));

        OrderToOrderViewConverter orderToOrderViewConverter = new OrderToOrderViewConverter();
        OrderView orderView = orderToOrderViewConverter.convert(shopOrder);
        assertEquals(ID, orderView.getId());
        assertEquals(Integer.valueOf(PRICE*ORDER_AMOUNT), orderView.getTotalPrice());


        OrderItemView orderItemView = orderView.getOrderItemViews().iterator().next();
        assertEquals(ID, orderItemView.getId());
        assertEquals(PRICE, orderItemView.getActualPrice());
        assertEquals(ORDER_AMOUNT, orderItemView.getAmount());
        assertEquals(ID, orderItemView.getStockKeepingUnitId());
    }

}