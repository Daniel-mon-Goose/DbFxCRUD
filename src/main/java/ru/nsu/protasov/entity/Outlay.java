package ru.nsu.protasov.entity;

import javax.persistence.*;

@Entity
@Table(name = "Outlays")
public class Outlay extends Identifiable {
    private int materialCount;

    public Outlay() {}

    public Outlay(int materialCount) {
        this.materialCount = materialCount;
    }

    @OneToOne
    @JoinColumn(name = "objectId", referencedColumnName = "id")
    private BuildingObject buildingObject;

    @ManyToOne
    @JoinColumn(name = "MaterialId", referencedColumnName = "id")
    private Material material;

    public int getMaterialCount() {
        return materialCount;
    }

    public void setMaterialCount(int materialCount) {
        this.materialCount = materialCount;
    }

    public BuildingObject getBuildingObject() {
        return buildingObject;
    }

    public void setBuildingObject(BuildingObject buildingObject) {
        this.buildingObject = buildingObject;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
