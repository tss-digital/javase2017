/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.customersfx;

import java.util.Objects;

/**
 *
 * @author tss
 */
public class Customer {

    public Customer(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public Customer(String number, String name, String contactFirst, String contactLast, String phone, String address, String country) {
        this.number = number;
        this.name = name;
        this.contactFirst = contactFirst;
        this.contactLast = contactLast;
        this.phone = phone;
        this.address = address;
        this.country = country;
    }

    
    private String number;
    private String name;
    private String contactFirst;
    private String contactLast;
    private String phone;
    private String address;
    private String country;

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getContactFirst() {
        return contactFirst;
    }

    public void setContactFirst(String contactFirst) {
        this.contactFirst = contactFirst;
    }

    public String getContactLast() {
        return contactLast;
    }

    public void setContactLast(String contactLast) {
        this.contactLast = contactLast;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.number);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "number=" + number + ", name=" + name + ", contactFirst=" + contactFirst + ", contactLast=" + contactLast + ", address=" + address + ", phone=" + phone + ", country=" + country + '}';
    }

}
