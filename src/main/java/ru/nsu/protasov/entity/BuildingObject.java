package ru.nsu.protasov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BuildingObjects")
@Inheritance(strategy = InheritanceType.JOINED)
public class BuildingObject extends Identifiable {
    public BuildingObject() {}

    public BuildingObject(String name) {
        this.name = name;
    }
    protected String name;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "areaId", referencedColumnName = "id")
    private Area area;

    @ManyToMany
    @JoinTable(name = "BuildingObjectWorkTypes")
    private Set<WorkType> workTypes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buildingObject", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Machinery> machinery;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Set<WorkType> getWorkTypes() {
        return workTypes;
    }

    public void setWorkTypes(Set<WorkType> workTypes) {
        this.workTypes = workTypes;
    }

    public Set<Machinery> getMachinery() {
        return machinery;
    }

    public void setMachinery(Set<Machinery> machinery) {
        this.machinery = machinery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
