package ru.nsu.protasov.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Machinery")
public class Machinery extends Identifiable {
    public Machinery() {}

    public Machinery(String type, String licencePlateNumber) {
        this.type = type;
        this.licencePlateNumber = licencePlateNumber;
    }

    private String type;
    private String licencePlateNumber;

    @ManyToOne
    @JoinColumn(name = "BuildingObjectId", referencedColumnName = "id")
    private BuildingObject buildingObject;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public void setLicencePlateNumber(String licencePlateNumber) {
        this.licencePlateNumber = licencePlateNumber;
    }

    public BuildingObject getBuildingObject() {
        return buildingObject;
    }

    public void setBuildingObject(BuildingObject buildingObject) {
        this.buildingObject = buildingObject;
    }
}
