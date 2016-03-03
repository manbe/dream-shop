package com.seleznov.task.shop.order.view;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author illcko
 */
public class OrderItemView {

    private Long id;

    @NotNull
    private Long stockKeepingUnitId;

    @Min(0)
    private Integer actualPrice;

    @Min(1)
    private Integer amount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getStockKeepingUnitId() {
        return stockKeepingUnitId;
    }

    public void setStockKeepingUnitId(Long stockKeepingUnitId) {
        this.stockKeepingUnitId = stockKeepingUnitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItemView that = (OrderItemView) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (stockKeepingUnitId != null ? !stockKeepingUnitId.equals(that.stockKeepingUnitId) : that.stockKeepingUnitId != null)
            return false;
        if (actualPrice != null ? !actualPrice.equals(that.actualPrice) : that.actualPrice != null) return false;
        return !(amount != null ? !amount.equals(that.amount) : that.amount != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (stockKeepingUnitId != null ? stockKeepingUnitId.hashCode() : 0);
        result = 31 * result + (actualPrice != null ? actualPrice.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
