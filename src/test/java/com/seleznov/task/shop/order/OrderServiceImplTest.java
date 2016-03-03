package com.seleznov.task.shop.order;

import com.seleznov.task.shop.customer.CustomerService;
import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.order.model.OrderItem;
import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.sku.StockKeepingUnit;
import com.seleznov.task.shop.sku.StockKeepingUnitService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author illcko
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    public static final Long ID = 1l;
    public static final Integer AMOUNT = 2;
    public static final Integer PRICE = 5;
    public static final int STU_AMOUNT = 10;
    public static final int CUSTOMER_BALANCE = 15;
    @InjectMocks
    private OrderService orderService = new OrderServiceImpl();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private StockKeepingUnitService stockKeepingUnitService;


    private ShopOrder order;

    private Customer customer;

    private StockKeepingUnit stockKeepingUnit;

    @Before
    public void init(){
        order = new ShopOrder();
        order.setId(ID);
        customer = new Customer();
        customer.setId(ID);
        customer.setBalance(CUSTOMER_BALANCE);

        OrderItem orderItem = new OrderItem();
        orderItem.setAmount(AMOUNT);
        orderItem.setActualPrice(PRICE);
        orderItem.setShopOrder(order);

        stockKeepingUnit = new StockKeepingUnit();
        stockKeepingUnit.setId(ID);
        stockKeepingUnit.setAmount(STU_AMOUNT);
        stockKeepingUnit.setPrice(PRICE);
        orderItem.setStockKeepingUnit(stockKeepingUnit);

        order.setOrderItems(Arrays.asList(orderItem));

        order.setCustomer(customer);
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order));
        when(customerService.getCustomer(ID)).thenReturn(customer);
        when(stockKeepingUnitService.decreaseStockKeepingUnitAmount(ID, AMOUNT)).thenAnswer(invocationOnMock -> {
            stockKeepingUnit.setAmount(STU_AMOUNT - AMOUNT);
            return stockKeepingUnit;
        });
        when(orderRepository.save(order)).thenReturn(order);
        when(customerService.updateCustomer(customer)).thenReturn(customer);
    }

    @Test
    public void testGetAllOrders() throws Exception {
        Collection<ShopOrder> ordersForCurrentCustomer = orderService.getOrdersForCurrentCustomer(ID);
        assertEquals(order, ordersForCurrentCustomer.iterator().next());
    }


    //TODO: more test and assertions
    @Test
    public void testMakeOrder() throws Exception {
        ShopOrder shopOrder = orderService.makeOrder(ID, order);
        assertEquals(Integer.valueOf(5), shopOrder.getCustomer().getBalance());
        verify(customerService, times(1)).updateCustomer(customer);
        verify(stockKeepingUnitService, times(1)).decreaseStockKeepingUnitAmount(ID, AMOUNT);

    }
}