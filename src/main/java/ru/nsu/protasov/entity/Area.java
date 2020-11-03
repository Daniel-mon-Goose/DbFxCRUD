package ru.nsu.protasov.entity;

import javax.persistence.*;

@Entity
@Table(name = "Areas")
public class Area extends Identifiable {
    public Area() {}

    public Area(TechnicalSpecialist manager) {
        manager.post = Post.AREA_MANAGER;
        this.manager = manager;
    }

    @OneToOne
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    private TechnicalSpecialist manager;

    @ManyToOne
    @JoinColumn(name = "managementId", referencedColumnName = "id")
    private ConstructionManagement management;

    public TechnicalSpecialist getManager() {
        return manager;
    }

    public void setManager(TechnicalSpecialist manager) {
        manager.post = Post.AREA_MANAGER;
        this.manager = manager;
    }

    public ConstructionManagement getManagement() {
        return management;
    }

    public void setManagement(ConstructionManagement management) {
        this.management = management;
    }
}
