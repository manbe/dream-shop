package com.seleznov.task.shop.integration;

import com.seleznov.task.shop.customer.CustomerRepository;
import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.customer.view.CustomerView;
import com.seleznov.task.shop.exception.ErrorConstants;
import com.seleznov.task.shop.exception.view.ErrorView;
import com.seleznov.task.shop.order.OrderService;
import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.order.view.OrderView;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TestContextTransactionUtils;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;



import static com.seleznov.task.shop.integration.util.CreateViewHelper.prepareOrderView;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author illcko
 */
@Transactional
public class MakeOrderIntegrationTest extends BaseIntegrationTest {


    public static final Long CUSTOMER_ID = 1l;

    public static final String CUSTOMER_URL = CUSTOMER + CUSTOMER_ID;
    public static final String ORDER_URL = CUSTOMER_URL + "/order";
    public static final Integer CUSTOMER_BALANCE = 20;
    public static final Long STOCK_KEEPING_UNIT_ID = 1l;

    @Autowired
    private RestTemplate restTemplate;



    @Test
    @Transactional
    public void makeSuccessOrderTest() {
        OrderView orderView = prepareOrderView(2, STOCK_KEEPING_UNIT_ID);

        doGetCustomerAndAssertBalance(CUSTOMER_BALANCE);
        doGetCustomerOrdersAndAssertIsEmpty();

        //Sent request for making order
        ResponseEntity<OrderView> orderViewResponseEntity = restTemplate.postForEntity(BASE_URL + ORDER_URL, orderView, OrderView.class);
        assertEquals(HttpStatus.OK, orderViewResponseEntity.getStatusCode());
        OrderView createdOrder = orderViewResponseEntity.getBody();
        assertEquals(Integer.valueOf(20), createdOrder.getTotalPrice());

        //Get created order
        ResponseEntity<OrderView[]>  createdOrders = restTemplate.getForEntity(BASE_URL + ORDER_URL, OrderView[].class);
        assertEquals(HttpStatus.OK, createdOrders.getStatusCode());
        assertEquals(1, createdOrders.getBody().length);
        createdOrder = createdOrders.getBody()[0];
        assertEquals(Integer.valueOf(20), createdOrder.getTotalPrice());

        doGetCustomerAndAssertBalance(0);
    }


    @Test
    public void OrderWithNoMoneyTest() {
        OrderView orderViewWithTooBigPrice = prepareOrderView(3, STOCK_KEEPING_UNIT_ID);

        doGetCustomerAndAssertBalance(CUSTOMER_BALANCE);

        doGetCustomerOrdersAndAssertIsEmpty();

        doPostOrderViewAndAssertErrorResponse(orderViewWithTooBigPrice);

        doGetCustomerOrdersAndAssertIsEmpty();

        doGetCustomerAndAssertBalance(CUSTOMER_BALANCE);
    }


    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void TransactionWorksTest() {
        Customer customer = customerRepository.findOne(CUSTOMER_ID);
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setCustomer(customer);
        try {
            orderService.makeOrder(CUSTOMER_ID, new ShopOrder());
            fail("should be validation exception");
        } catch (Exception ex){
            customer = customerRepository.findOne(CUSTOMER_ID);
            assertEquals(Integer.valueOf(20), customer.getBalance());
        }

    }


    private void doPostOrderViewAndAssertErrorResponse(OrderView orderViewWithTooBigPrice) {
        ResponseEntity<ErrorView> errorViewResponseEntity = restTemplate.postForEntity(BASE_URL + ORDER_URL, orderViewWithTooBigPrice, ErrorView.class);
        assertEquals(HttpStatus.BAD_REQUEST, errorViewResponseEntity.getStatusCode());
        assertEquals(ErrorConstants.ERR_OPERATION_DENIED, errorViewResponseEntity.getBody().getCode());
        assertEquals("Order price=30 but customer balance=20", errorViewResponseEntity.getBody().getMessage());
    }

    private void doGetCustomerAndAssertBalance(Integer balance) {
        ResponseEntity<CustomerView> customer = restTemplate.getForEntity(BASE_URL + CUSTOMER_URL, CustomerView.class);
        assertEquals(HttpStatus.OK, customer.getStatusCode());
        assertEquals(balance, customer.getBody().getBalance());
    }

    private void doGetCustomerOrdersAndAssertIsEmpty() {
        ResponseEntity<OrderView[]> createdOrders = restTemplate.getForEntity(BASE_URL + ORDER_URL, OrderView[].class);
        assertEquals(HttpStatus.OK, createdOrders.getStatusCode());
        assertEquals(0, createdOrders.getBody().length);
    }


}
