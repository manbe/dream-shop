package com.seleznov.task.shop.integration;

import com.seleznov.task.shop.order.view.OrderItemView;
import com.seleznov.task.shop.order.view.OrderView;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author illcko
 */
public class MakeOrderIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void makeSuccessOrder(){
        OrderView orderView = prepareOrderView();

        ResponseEntity<OrderView> orderViewResponseEntity = restTemplate.postForEntity(BASE_URL + "/customer/1/order", orderView, OrderView.class);
        assertEquals(HttpStatus.OK, orderViewResponseEntity.getStatusCode());
        ResponseEntity<OrderView[]> createdOrders = restTemplate.getForEntity(BASE_URL + "/customer/1/order", OrderView[].class);
        assertEquals(HttpStatus.OK, createdOrders.getStatusCode());
        assertEquals(1, createdOrders.getBody().length);
        OrderView createdOrder = createdOrders.getBody()[0];
        assertEquals(Integer.valueOf(20), createdOrder.getTotalPrice());
    }

    private OrderView prepareOrderView() {
        OrderView orderView = new OrderView();
        OrderItemView orderItemView = new OrderItemView();
        orderItemView.setAmount(2);
        orderItemView.setStockKeepingUnitId(1l);
        orderView.setOrderItemViews(Arrays.asList(orderItemView));
        return orderView;
    }
}
