package com.seleznov.task.shop.integration;

import com.seleznov.task.shop.customer.CustomerRepository;
import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.order.OrderService;
import com.seleznov.task.shop.order.model.ShopOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author illcko
 */
public class OrderServiceTransactionIntegrationTest extends BaseIntegrationTest{

    public static final Long CUSTOMER_ID = 1l;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void TransactionWorksTest() {
//        Customer customer = customerRepository.findOne(CUSTOMER_ID);
//        ShopOrder shopOrder = new ShopOrder();
//        shopOrder.setCustomer(customer);
//        try {
//            orderService.makeOrder(CUSTOMER_ID, new ShopOrder());
//            fail("should be validation exception");
//        } catch (Exception ex){
//            customer = customerRepository.findOne(CUSTOMER_ID);
//            assertEquals(Integer.valueOf(20), customer.getBalance());
//        }

    }



}
