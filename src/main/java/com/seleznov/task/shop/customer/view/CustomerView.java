package com.seleznov.task.shop.customer.view;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

/**
 * @author illcko
 */
public class CustomerView {

    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotNull
    private Integer balance;

    @Valid
    private Set<ShippingAddressView> shippingAddressViews;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Set<ShippingAddressView> getShippingAddressViews() {
        return shippingAddressViews;
    }

    public void setShippingAddressViews(Set<ShippingAddressView> shippingAddressViews) {
        this.shippingAddressViews = shippingAddressViews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerView that = (CustomerView) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        return !(shippingAddressViews != null ? !shippingAddressViews.equals(that.shippingAddressViews) : that.shippingAddressViews != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (shippingAddressViews != null ? shippingAddressViews.hashCode() : 0);
        return result;
    }
}
