package ru.nsu.protasov.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ResidentialHouses")
public class ResidentialHouse extends BuildingObject {
    public ResidentialHouse() {}

    public ResidentialHouse(String name) {
        super(name);
    }

    private int flatCount;
    private int floorCount;

    public int getFlatCount() {
        return flatCount;
    }

    public void setFlatCount(int flatCount) {
        this.flatCount = flatCount;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }
}
