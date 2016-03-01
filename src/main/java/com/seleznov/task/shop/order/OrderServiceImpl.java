package com.seleznov.task.shop.order;

import com.seleznov.task.shop.customer.CustomerService;
import com.seleznov.task.shop.customer.model.Customer;
import com.seleznov.task.shop.exception.IllegalOrderException;
import com.seleznov.task.shop.exception.IllegalSKUException;
import com.seleznov.task.shop.order.model.OrderItem;
import com.seleznov.task.shop.order.model.ShopOrder;
import com.seleznov.task.shop.sku.StockKeepingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.function.Predicate;

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
    public Collection<ShopOrder> getAllOrders(Long customerId) {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ShopOrder makeOrder(Long customerId, ShopOrder shopOrder) {
        Customer customer = customerService.getCustomer(customerId);

        Integer totalPrice = TotalPriceCalculationUtil.calculateTotalPrice(shopOrder);

        Integer newBalance = customer.getBalance() - totalPrice;

        if (newBalance < 0) {
            throw new IllegalOrderException("Total price of shopOrder " + totalPrice + " is bigger then customer balance " + customer.getBalance());
        }

        Predicate<OrderItem> isNotSameSKU = item -> !item.getStockKeepingUnit().getPrice().equals((stockKeepingUnitService.getStockKeepingUnit(item.getStockKeepingUnit().getId()).getPrice()));

        long countOfWrongSKU = shopOrder.getOrderItems().stream()
                .filter(isNotSameSKU)
                .count();

        if(countOfWrongSKU > 0){
            throw new IllegalSKUException("There are "+ countOfWrongSKU + " store keeping units that have ton relevant data");
        }

        shopOrder.getOrderItems()
                .forEach(orderItem -> stockKeepingUnitService.decreaseStockKeepingUnitAmount(orderItem.getStockKeepingUnit(), orderItem.getAmount()));

        shopOrder.setCustomer(customer);

        customer.setBalance(newBalance);
        customerService.updateCustomer(customer);

        ShopOrder order = orderRepository.save(shopOrder);
        return order;
    }

}
