package com.sparta.northwindapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Order;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class EmployeeDTO {
    private Integer id;
    private String lastName;
    private String firstName;
    private String title;
    private String titleOfCourtesy;
    private Date birthDate;
    private Date hireDate;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String homePhone;
    private String extension;
    private byte[] photo;
    private String notes;
    private Employee reportsTo;
    private String photoPath;
    private Float salary;
    private Set<Employee> employees = new LinkedHashSet<>();
    private Set<Order> orders = new LinkedHashSet<>();

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer ID, String FirstName, String LastName, String Title, String titleOfCourtesy, Date birthDate, Date hireDate, String address,String city,String region,String postalCode,String country,String homePhone,String extension,byte[] photo,String notes,Employee reportsTo,String photoPath,Float salary,Set<Employee> employees,Set<Order> orders) {
        this.id = ID;
        this.firstName=FirstName;
        this.lastName=LastName;
        this.title=Title;
        this.titleOfCourtesy=titleOfCourtesy;
        this.birthDate=birthDate;
        this.hireDate=hireDate;
        this.address=address;
        this.city=city;
        this.region=region;
        this.postalCode=postalCode;
        this.country=country;
        this.homePhone=homePhone;
        this.extension=extension;
        this.photo=photo;
        this.notes=notes;
        this.reportsTo=reportsTo;
        this.photoPath=photoPath;
        this.salary=salary;
        this.employees=employees;
        this.orders=orders;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName() {
        this.firstName = firstName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Employee getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Employee reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO entity = (EmployeeDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.title, entity.title) &&
                Objects.equals(this.titleOfCourtesy, entity.titleOfCourtesy) &&
                Objects.equals(this.birthDate, entity.birthDate) &&
                Objects.equals(this.hireDate, entity.hireDate) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.region, entity.region) &&
                Objects.equals(this.postalCode, entity.postalCode) &&
                Objects.equals(this.country, entity.country)&&
                Objects.equals(this.homePhone, entity.homePhone)&&
                Objects.equals(this.extension, entity.extension)&&
                Objects.equals(this.photo, entity.photo)&&
                Objects.equals(this.notes, entity.notes)&&
                Objects.equals(this.reportsTo, entity.reportsTo)&&
                Objects.equals(this.photoPath, entity.photoPath)&&
                Objects.equals(this.salary, entity.salary)&&
                Objects.equals(this.employees, entity.employees)&&
                Objects.equals(this.orders, entity.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, title, titleOfCourtesy, birthDate, hireDate, address, city, region, postalCode,country,homePhone,extension,photo,notes,reportsTo,photoPath,salary,employees,orders);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "first name = " + firstName+", "+
                "last name = " + lastName+", "+
                "title = " + title+", "+
                "title of courtesy = " + titleOfCourtesy+", "+
                "birth date = " + birthDate+", "+
                "hire date = " + hireDate+", "+
                "address = " + address+", "+
                "city = " + city+", "+
                "region = " + region+", "+
                "postal code = " + postalCode+", "+
                "country = " + country+", "+
                "home phone = " + homePhone+", "+
                "extension = " + extension+", "+
                "photo = " + photo+", "+
                "notes = " + notes+", "+
                "reports to = " + reportsTo+", "+
                "photo path = " + photoPath+", "+
                "salary = " + salary+", "+
                "employees = " + employees+", "+
                "orders = " + orders+", ";
    }
}
