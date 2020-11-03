package ru.nsu.protasov.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Masters")
public class Master extends TechnicalSpecialist {
    private int category;

    public Master() {
        this.setPost(Post.MASTER);
    }

    public Master(String name, String surname, String patronymic,
                  int category, String educationalInstitution, int experienceYears) {
        super(name, surname, patronymic, educationalInstitution, experienceYears);
        this.category = category;
        this.setPost(Post.MASTER);
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
