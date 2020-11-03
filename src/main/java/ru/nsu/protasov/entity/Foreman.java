package ru.nsu.protasov.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Foremen")
public class Foreman extends TechnicalSpecialist {
    private boolean knowledgeOfEnglish;

    public Foreman() {
        this.setPost(Post.FOREMAN);
    }

    public Foreman(String name, String surname, String patronymic,
                   boolean knowledgeOfEnglish, String educationalInstitution, int experienceYears) {
        super(name, surname, patronymic, educationalInstitution, experienceYears);
        this.knowledgeOfEnglish = knowledgeOfEnglish;
        this.setPost(Post.FOREMAN);
    }

    public boolean isKnowledgeOfEnglish() {
        return knowledgeOfEnglish;
    }

    public void setKnowledgeOfEnglish(boolean knowledgeOfEnglish) {
        this.knowledgeOfEnglish = knowledgeOfEnglish;
    }
}
