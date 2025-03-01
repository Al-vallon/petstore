package com.petshop.petshop.BO;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Fish extends Animal {

    @Enumerated(EnumType.STRING)
    private FishLivEnv livingEnv;

    public Fish() {}

    public Fish(LocalDate birth, String color, FishLivEnv livingEnv) {
        super(birth, color);
        this.livingEnv = livingEnv;
    }

    public FishLivEnv getLivingEnv() {
        return livingEnv;
    }

    public void setLivingEnv(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "livingEnv=" + livingEnv +
                ", id=" + getId() +
                ", birth=" + getBirth() +
                ", color='" + getColor() + '\'' +
                '}';
    }
}
