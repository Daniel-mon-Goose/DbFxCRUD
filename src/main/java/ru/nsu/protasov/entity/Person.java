package ru.nsu.protasov.entity;

import javax.persistence.*;

@Entity
@Table(name = "Persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person extends Identifiable {
    public Person() {}

    public Person(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public Person(String name, String surname, String patronymic, Post post) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.post = post;
    }

    protected String name;
    protected String surname;
    protected String patronymic;
    @Enumerated(EnumType.STRING)
    protected Post post;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
