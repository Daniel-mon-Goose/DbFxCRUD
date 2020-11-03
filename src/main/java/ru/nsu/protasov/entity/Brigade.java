package ru.nsu.protasov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Brigades")
public class Brigade extends Identifiable {
    public Brigade() {}

    public Brigade(Worker manager, ConstructionManagement constructionManagement) {
        this.manager = manager;
        this.constructionManagement = constructionManagement;
    }

    @OneToOne
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    private Worker manager;

    @ManyToOne
    @JoinColumn(name = "managementId", referencedColumnName = "id")
    private ConstructionManagement constructionManagement;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brigade", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Worker> workers;

    public Worker getManager() {
        return manager;
    }

    public void setManager(Worker manager) {
        this.manager = manager;
    }

    public ConstructionManagement getConstructionManagement() {
        return constructionManagement;
    }

    public void setConstructionManagement(ConstructionManagement constructionManagement) {
        this.constructionManagement = constructionManagement;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }
}
