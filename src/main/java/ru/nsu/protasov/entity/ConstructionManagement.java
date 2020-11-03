package ru.nsu.protasov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ConstructionManagement")
public class ConstructionManagement extends Identifiable {
    public ConstructionManagement() {}

    public ConstructionManagement(TechnicalSpecialist manager) {
        manager.post = Post.MANAGEMENT_MANAGER;
        this.manager = manager;
    }

    @OneToOne
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    private TechnicalSpecialist manager;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Area> areas;

    public TechnicalSpecialist getManager() {
        return manager;
    }

    public void setManager(TechnicalSpecialist manager) {
        manager.post = Post.MANAGEMENT_MANAGER;
        this.manager = manager;
    }

    public Set<Area> getAreas() {
        return areas;
    }

    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }
}
