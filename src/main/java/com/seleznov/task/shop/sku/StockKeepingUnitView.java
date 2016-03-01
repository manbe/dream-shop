package com.seleznov.task.shop.sku;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author illcko
 */
public class StockKeepingUnitView {

    private Long id;

    @Size(max = 50)
    private String name;

    @Min(0)
    private Integer price;

    @Min(0)
    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
