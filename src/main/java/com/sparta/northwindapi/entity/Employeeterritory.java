package com.sparta.northwindapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "EmployeeTerritories")
public class Employeeterritory {
    @EmbeddedId
    private EmployeeterritoryId id;

    @MapsId("employeeID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EmployeeID", nullable = false)
    private Employee employeeID;

    @MapsId("territoryID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TerritoryID", nullable = false)
    private Territory territoryID;

    public EmployeeterritoryId getId() {
        return id;
    }

    public void setId(EmployeeterritoryId id) {
        this.id = id;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    public Territory getTerritoryID() {
        return territoryID;
    }

    public void setTerritoryID(Territory territoryID) {
        this.territoryID = territoryID;
    }

    @Override
    public String toString() {
        return "Employeeterritory{" +
                "id=" + id +
                ", employeeID=" + employeeID +
                ", territoryID=" + territoryID +
                '}';
    }
}