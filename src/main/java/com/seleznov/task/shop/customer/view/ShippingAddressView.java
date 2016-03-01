package com.seleznov.task.shop.customer.view;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author illcko
 */
public class ShippingAddressView {

    private Long id;

    @Size(min = 5, max = 5)
    private String postalCode;

    @Size(max = 50)
    private String street;

    @Size(max = 5)
    private String house;

    @Min(1)
    private int flat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShippingAddressView that = (ShippingAddressView) o;

        if (flat != that.flat) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        return !(house != null ? !house.equals(that.house) : that.house != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + flat;
        return result;
    }
}
