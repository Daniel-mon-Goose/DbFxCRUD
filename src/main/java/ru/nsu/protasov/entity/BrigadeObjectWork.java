package ru.nsu.protasov.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BrigadeObjectWorks")
public class BrigadeObjectWork extends Identifiable {
    @ManyToOne
    @JoinColumn(name = "objectId", referencedColumnName = "id")
    private BuildingObject buildingObject;

    @ManyToOne
    @JoinColumn(name = "workTypeId", referencedColumnName = "id")
    private WorkType workType;

    @ManyToOne
    @JoinColumn(name = "brigadeId", referencedColumnName = "id")
    private Brigade brigade;

    public BuildingObject getBuildingObject() {
        return buildingObject;
    }

    public void setBuildingObject(BuildingObject buildingObject) {
        this.buildingObject = buildingObject;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }
}
