package com.seleznov.task.shop.order.view;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author illcko
 */
public class OrderView implements Serializable{

    private Long id;

    @NotNull
    @Min(0)
    private Integer totalPrice;

    @NotEmpty
    private Collection<OrderItemView> orderItemViews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Collection<OrderItemView> getOrderItemViews() {
        return orderItemViews;
    }

    public void setOrderItemViews(Collection<OrderItemView> orderItemViews) {
        this.orderItemViews = orderItemViews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderView orderView = (OrderView) o;

        if (id != null ? !id.equals(orderView.id) : orderView.id != null) return false;
        if (totalPrice != null ? !totalPrice.equals(orderView.totalPrice) : orderView.totalPrice != null)
            return false;
        return orderItemViews.equals(orderView.orderItemViews);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + orderItemViews.hashCode();
        return result;
    }
}
