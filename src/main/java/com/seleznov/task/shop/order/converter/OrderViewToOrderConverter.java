package com.seleznov.task.shop.order.converter;

import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.order.model.OrderItem;
import com.seleznov.task.shop.order.view.OrderView;
import com.seleznov.task.shop.sku.StockKeepingUnit;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author illcko
 */
public class OrderViewToOrderConverter implements Converter<OrderView, ShopOrder> {
    @Override
    public ShopOrder convert(OrderView orderView) {
        ModelMapper modelMapper = new ModelMapper();
        ShopOrder shopOrder = new ShopOrder();

        Set<OrderItem> orderItems = orderView.getOrderItemViews().stream()
                .map(orderItemView -> {
                    OrderItem orderItem = modelMapper.map(orderItemView, OrderItem.class);
                    orderItem.setId(null);
                    orderItem.setShopOrder(shopOrder);
                    StockKeepingUnit stockKeepingUnit = modelMapper.map(orderItemView.getStockKeepingUnitView(), StockKeepingUnit.class);
                    orderItem.setStockKeepingUnit(stockKeepingUnit);
                    orderItem.setActualPrice(stockKeepingUnit.getPrice() * orderItemView.getAmount());
                    return orderItem;
                })
                .collect(Collectors.toSet());

        shopOrder.setOrderItems(orderItems);

        return shopOrder;
    }
}
