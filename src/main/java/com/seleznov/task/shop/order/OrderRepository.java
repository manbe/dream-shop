package com.seleznov.task.shop.order;

import com.seleznov.task.shop.order.model.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author illcko
 */
public interface OrderRepository extends JpaRepository<ShopOrder, Long> {
}
