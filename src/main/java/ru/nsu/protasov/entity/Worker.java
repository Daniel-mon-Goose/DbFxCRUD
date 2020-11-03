package ru.nsu.protasov.entity;

import javax.persistence.*;

@Entity
@Table(name = "Workers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Worker extends Person {
    private int experienceYears;

    public Worker() {}

    public Worker(String name, String surname, String patronymic,
                  int experienceYears) {
        super(name, surname, patronymic);
        this.experienceYears = experienceYears;
    }

    @ManyToOne
    @JoinColumn(name = "brigadeId", referencedColumnName = "id")
    private Brigade brigade;

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }
}
