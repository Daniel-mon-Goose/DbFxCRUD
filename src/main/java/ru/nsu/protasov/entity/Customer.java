package ru.nsu.protasov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Customers")
public class Customer extends Identifiable {
    public Customer() {}

    public Customer(String name) {
        this.name = name;
    }

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BuildingObject> buildingObjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BuildingObject> getBuildingObjects() {
        return buildingObjects;
    }

    public void setBuildingObjects(Set<BuildingObject> buildingObjects) {
        this.buildingObjects = buildingObjects;
    }
}
