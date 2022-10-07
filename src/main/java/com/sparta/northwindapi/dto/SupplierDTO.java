package com.sparta.northwindapi.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sparta.northwindapi.entity.Supplier} entity
 */
public class SupplierDTO implements Serializable {
    private Integer id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
    private String homePage;

    public SupplierDTO() {
    }

    public SupplierDTO(Integer id, String companyName, String contactName, String contactTitle, String address, String city, String region, String postalCode, String country, String phone, String fax, String homePage) {
        this.id = id;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
        this.homePage = homePage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierDTO entity = (SupplierDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.companyName, entity.companyName) &&
                Objects.equals(this.contactName, entity.contactName) &&
                Objects.equals(this.contactTitle, entity.contactTitle) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.region, entity.region) &&
                Objects.equals(this.postalCode, entity.postalCode) &&
                Objects.equals(this.country, entity.country) &&
                Objects.equals(this.phone, entity.phone) &&
                Objects.equals(this.fax, entity.fax) &&
                Objects.equals(this.homePage, entity.homePage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax, homePage);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "companyName = " + companyName + ", " +
                "contactName = " + contactName + ", " +
                "contactTitle = " + contactTitle + ", " +
                "address = " + address + ", " +
                "city = " + city + ", " +
                "region = " + region + ", " +
                "postalCode = " + postalCode + ", " +
                "country = " + country + ", " +
                "phone = " + phone + ", " +
                "fax = " + fax + ", " +
                "homePage = " + homePage + ")";
    }
}