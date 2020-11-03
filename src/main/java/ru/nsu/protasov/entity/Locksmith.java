package ru.nsu.protasov.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Locksmiths")
public class Locksmith extends Worker {
    private int category;
    private boolean higherEducation;

    public Locksmith() {
        this.setPost(null);
    }

    public Locksmith(String name, String surname, String patronymic,
                     int category, boolean higherEducation, int experienceYears) {
        super(name, surname, patronymic, experienceYears);
        this.category = category;
        this.higherEducation = higherEducation;
        this.setPost(Post.LOCKSMITH);
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isHigherEducation() {
        return higherEducation;
    }

    public void setHigherEducation(boolean higherEducation) {
        this.higherEducation = higherEducation;
    }
}
