package ru.nsu.protasov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "WorkTypes")
public class WorkType extends Identifiable {
    private String name;

    public WorkType(String name) {
        this.name = name;
    }

    public WorkType() {}

    @ManyToMany(mappedBy = "workTypes")
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
