package ru.nsu.protasov.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Bridges")
public class Bridge extends BuildingObject {
    public Bridge() {}

    public Bridge(String name) {
        super(name);
    }

    private double widthInMetres;
    private String typeOfSpan;
    private int numberOfTrafficLanes;

    public double getWidthInMetres() {
        return widthInMetres;
    }

    public void setWidthInMetres(double widthInMetres) {
        this.widthInMetres = widthInMetres;
    }

    public String getTypeOfSpan() {
        return typeOfSpan;
    }

    public void setTypeOfSpan(String typeOfSpan) {
        this.typeOfSpan = typeOfSpan;
    }

    public int getNumberOfTrafficLanes() {
        return numberOfTrafficLanes;
    }

    public void setNumberOfTrafficLanes(int numberOfTrafficLanes) {
        this.numberOfTrafficLanes = numberOfTrafficLanes;
    }
}
