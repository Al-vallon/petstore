package com.petshop.petshop.BO;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="PetStore")
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String managerName;

    //Address
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;

    //Animal
    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL)
    private Set<Animal> animals = new HashSet<>();

    //product
    @ManyToMany(mappedBy = "petStores")
    private Set<Product> products;

    public PetStore() {
        this.products = new HashSet<>();
    }

    public PetStore(Long id, String name, String managerName, Address address) {
        this.id = id;
        this.name = name;
        this.managerName = managerName;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        if (address != null) {
            address.setPetStore(this);
        }
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        for (Animal animal : animals) {
            this.addAnimal(animal);
        }
    }

    public void addAnimal(Animal animal) {
        if (!this.animals.contains(animal)) {
            this.animals.add(animal);
            animal.setPetStore(this);
        }
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void addProductToPetStore(Product product) {
        products.add(product);
        if (!product.getPetStores().contains(this)) {
            product.getPetStores().add(this);
        }
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "PetStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managerName='" + managerName + '\'' +
                '}';
    }


}
