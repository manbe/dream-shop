package com.seleznov.task.shop.order;

import com.seleznov.task.shop.order.model.ShopOrder;

/**
 * @author illcko
 */
public class TotalPriceCalculationUtil {

    public static Integer calculateTotalPrice(ShopOrder shopOrder){
        return shopOrder.getOrderItems().stream()
                .mapToInt(orderItem -> orderItem.getActualPrice())
                .sum();
    }
}
