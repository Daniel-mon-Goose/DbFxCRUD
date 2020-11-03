package ru.nsu.protasov.entity;

import javax.persistence.*;

@Entity
@Table(name = "TechnicalSpecialists")
@Inheritance(strategy = InheritanceType.JOINED)
public class TechnicalSpecialist extends Person {
    private String educationalInstitution;
    private int experienceYears;

    public TechnicalSpecialist() {}

    public TechnicalSpecialist(String name, String surname, String patronymic,
                               String educationalInstitution, int experienceYears) {
        super(name, surname, patronymic);
        this.educationalInstitution = educationalInstitution;
        this.experienceYears = experienceYears;
    }

    @ManyToOne
    @JoinColumn(name = "areaId", referencedColumnName = "Id")
    private Area area;

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(String educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
