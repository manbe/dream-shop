package com.seleznov.task.shop.order;

import com.seleznov.task.shop.customer.CustomerService;
import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.exception.IllegalOrderException;
import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.sku.StockKeepingUnit;
import com.seleznov.task.shop.sku.StockKeepingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author illcko
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StockKeepingUnitService stockKeepingUnitService;


    @Override
    public Collection<ShopOrder> getOrdersForCurrentCustomer(Long customerId) {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShopOrder makeOrder(Long customerId, ShopOrder shopOrder) {
        Customer customer = customerService.getCustomer(customerId);



        shopOrder.getOrderItems()
                .forEach(orderItem -> {
                    StockKeepingUnit stockKeepingUnit = stockKeepingUnitService.decreaseStockKeepingUnitAmount(orderItem.getStockKeepingUnit().getId(), orderItem.getAmount());
                    orderItem.setStockKeepingUnit(stockKeepingUnit);
                    orderItem.setActualPrice(stockKeepingUnit.getPrice());
                });

        int totalPrice = TotalPriceCalculationUtil.calculateTotalPrice(shopOrder);
        int newBalance = customer.getBalance() - totalPrice;

        if (newBalance < 0) {
            throw new IllegalOrderException("Order price=" + totalPrice + " but customer balance=" + customer.getBalance());
        }
        shopOrder.setCustomer(customer);

        customer.setBalance(newBalance);
        customerService.updateCustomer(customer);

        ShopOrder order = orderRepository.save(shopOrder);
        return order;
    }

}
