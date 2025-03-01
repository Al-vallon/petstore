package com.petshop.petshop.BO;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Cat extends Animal {
    private String chipId;

    public Cat() {}

    public Cat( LocalDate birth, String couleur, String chipId) {
        super(birth, couleur);
        this.chipId = chipId;
    }
    public String getChipId() {
        return chipId;
    }
    public void setChipId(String chipId) {
        this.chipId = chipId;
    }

}
