package com.seleznov.task.shop.customer.model;

import com.seleznov.task.shop.order.model.ShopOrder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @author illcko
 */
@Entity
public class Customer{
    public static final String ID = "id";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @NotBlank
    @Column(length = 50)
    private String name;

    @NotBlank
    @Email
    @Column
    private String email;

    @NotNull
    @Min(value = 0)
    @Column
    private Integer balance;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = ID)
    private Collection<ShippingAddress> shippingAddresses;

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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Collection<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(Collection<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }



    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        return !(balance != null ? !balance.equals(customer.balance) : customer.balance != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}
