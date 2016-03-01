package com.seleznov.task.shop.order.model;

import com.seleznov.task.shop.sku.StockKeepingUnit;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author illcko
 */
@Entity
public class OrderItem{

    public static final String STOCK_KEEPING_UNIT_ID = "STOCK_KEEPING_UNIT_ID";
    public static final String ORDER_ID = "ORDER_ID";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name= STOCK_KEEPING_UNIT_ID)
    private StockKeepingUnit stockKeepingUnit;

    @NotNull
    @ManyToOne
    @JoinColumn(name= ORDER_ID)
    private ShopOrder shopOrder;

    @Column
    @Min(0)
    private Integer actualPrice;

    @Column
    @Min(0)
    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockKeepingUnit getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(StockKeepingUnit stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
    }

    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != null ? !id.equals(orderItem.id) : orderItem.id != null) return false;
        if (stockKeepingUnit != null ? !stockKeepingUnit.equals(orderItem.stockKeepingUnit) : orderItem.stockKeepingUnit != null)
            return false;
        if (shopOrder != null ? !shopOrder.equals(orderItem.shopOrder) : orderItem.shopOrder != null) return false;
        if (actualPrice != null ? !actualPrice.equals(orderItem.actualPrice) : orderItem.actualPrice != null)
            return false;
        if (amount != null ? !amount.equals(orderItem.amount) : orderItem.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (stockKeepingUnit != null ? stockKeepingUnit.hashCode() : 0);
        result = 31 * result + (shopOrder != null ? shopOrder.hashCode() : 0);
        result = 31 * result + (actualPrice != null ? actualPrice.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
