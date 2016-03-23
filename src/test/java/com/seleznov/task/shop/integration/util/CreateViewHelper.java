package com.seleznov.task.shop.integration.util;

import com.seleznov.task.shop.order.view.OrderItemView;
import com.seleznov.task.shop.order.view.OrderView;

import java.util.Collections;

/**
 * @author illcko
 */
public class CreateViewHelper {

    public static OrderView prepareOrderView(int amount, Long stockKeepingUnitId) {
        OrderView orderView = new OrderView();
        OrderItemView orderItemView = new OrderItemView();
        orderItemView.setAmount(amount);
        orderItemView.setStockKeepingUnitId(stockKeepingUnitId);
        orderView.setOrderItemViews(Collections.singleton(orderItemView));
        return orderView;
    }
}
