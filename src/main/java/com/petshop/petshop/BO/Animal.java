package com.petshop.petshop.BO;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Animal")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate birth;
    private String color;

    @ManyToOne
    @JoinColumn(name = "petStore_id")
    private PetStore petStore;

    public Animal() {}

    public Animal(LocalDate birth, String color) {
        this.birth = birth;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public PetStore getPetStore() {
        return petStore;
    }

    public void setPetStore(PetStore petStore) {
        this.petStore = petStore;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", birth=" + birth +
                ", color='" + color + '\'' +
                ", petStore=" + petStore.getName() +
                '}';
    }
}
