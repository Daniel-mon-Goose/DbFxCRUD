package ru.nsu.protasov.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Masons")
public class Mason extends Worker {
    private int bricksPerHour;

    public Mason() {
        this.setPost(Post.MASON);
    }

    public Mason(String name, String surname, String patronymic,
                 int bricksPerHour, int experienceYears) {
        super(name, surname, patronymic, experienceYears);
        this.bricksPerHour = bricksPerHour;
        this.setPost(Post.MASON);
    }

    public int getBricksPerHour() {
        return bricksPerHour;
    }

    public void setBricksPerHour(int bricksPerHour) {
        this.bricksPerHour = bricksPerHour;
    }
}
