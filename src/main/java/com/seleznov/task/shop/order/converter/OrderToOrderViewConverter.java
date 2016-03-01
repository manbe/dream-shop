package com.seleznov.task.shop.order.converter;

import com.seleznov.task.shop.order.TotalPriceCalculationUtil;
import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.order.view.OrderItemView;
import com.seleznov.task.shop.order.view.OrderView;
import com.seleznov.task.shop.sku.StockKeepingUnitView;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author illcko
 */
public class OrderToOrderViewConverter implements Converter<ShopOrder, OrderView> {
    @Override
    public OrderView convert(ShopOrder shopOrder) {
        ModelMapper modelMapper = new ModelMapper();
        OrderView orderView = modelMapper.map(shopOrder, OrderView.class);

        Set<OrderItemView> orderItemViews = shopOrder.getOrderItems().stream()
                .map(orderItem -> {
                    OrderItemView orderItemView = modelMapper.map(orderItem, OrderItemView.class);
                    orderItemView.setStockKeepingUnitView(modelMapper.map(orderItem.getStockKeepingUnit(), StockKeepingUnitView.class));
                    return orderItemView;
                })
                .collect(Collectors.toSet());

        orderView.setTotalPrice(TotalPriceCalculationUtil.calculateTotalPrice(shopOrder));
        orderView.setOrderItemViews(orderItemViews);




        return orderView;
    }
}
