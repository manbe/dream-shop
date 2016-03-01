package com.seleznov.task.shop.order.model;

import com.seleznov.task.shop.customer.model.Customer;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author illcko
 */
@Entity
public class ShopOrder {

    public static final String CUSTOMER_ID = "CUSTOMER_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name= CUSTOMER_ID)
    private Customer customer;

    @NotEmpty
    @OneToMany(mappedBy = "shopOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<OrderItem> orderItems = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Collection<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopOrder shopOrder = (ShopOrder) o;

        if (id != null ? !id.equals(shopOrder.id) : shopOrder.id != null) return false;
        if (customer != null ? !customer.equals(shopOrder.customer) : shopOrder.customer != null) return false;
        return orderItems.equals(shopOrder.orderItems);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + orderItems.hashCode();
        return result;
    }
}
