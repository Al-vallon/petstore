package com.petshop.petshop;

import com.petshop.petshop.BO.*;
import com.petshop.petshop.Repository.*;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@SpringBootApplication
public class PetshopApplication implements CommandLineRunner {

    private final AnimalRepository animalRepository;
    private final ProductRepository productRepository;
    private final PetStoreRepository petStoreRepository;
    private final AddressRepository addressRepository;
    private final CatRepository catRepository;
    private final FishRepository fishRepository;
    private final ListableBeanFactory listableBeanFactory;

    @Autowired
    public PetshopApplication(ProductRepository productRepository, AnimalRepository animalRepository, PetStoreRepository petStoreRepository,
                              AddressRepository addressRepository, CatRepository catRepository, FishRepository fishRepository, ListableBeanFactory listableBeanFactory) {
        this.productRepository = productRepository;
        this.animalRepository = animalRepository;
        this.petStoreRepository = petStoreRepository;
        this.addressRepository = addressRepository;
        this.catRepository = catRepository;
        this.fishRepository = fishRepository;
        this.listableBeanFactory = listableBeanFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(PetshopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Création  des adresses
        Address address1 = new Address("10", "rue du POJO", "75000", "Paris");
        Address address2 = new Address("20", "avenue de la JPA", "69000", "Lyon");
        Address address3 = new Address("30", "boulevard des Injections", "44000", "Nantes");

        // Création des PetStores et association des adresses
        PetStore petStore1 = new PetStore();
        petStore1.setName("PetShop Paris");
        petStore1.setManagerName("Alice");
        petStore1.setAddress(address1);

        PetStore petStore2 = new PetStore();
        petStore2.setName("PetShop Lyon");
        petStore2.setManagerName("Bob");
        petStore2.setAddress(address2);

        PetStore petStore3 = new PetStore();
        petStore3.setName("PetShop Nantes");
        petStore3.setManagerName("Charlie");
        petStore3.setAddress(address3);

        // Sauvegarde
        petStoreRepository.save(petStore1);
        petStoreRepository.save(petStore2);
        petStoreRepository.save(petStore3);

        //Création des animeaux
        Fish fish1 = new Fish(LocalDate.of(2025, 2, 12), "Blue", FishLivEnv.FRESH_WATER);
        Fish fish2 = new Fish(LocalDate.of(2025, 2, 2), "White&Red", FishLivEnv.SEA_WATER);

        Cat cat1 = new Cat(LocalDate.of(2015, 2, 3), "Black", "CAT09678889");
        Cat cat2 = new Cat(LocalDate.of(2009, 10, 5), "BROWN", "CAT19646579");
        Cat cat3 = new Cat(LocalDate.of(2024, 8, 9), "WHITE", "CAT2964785389");

        // ajout  des animeaux au store
        cat1.setPetStore(petStore1);
        cat2.setPetStore(petStore1);
        cat3.setPetStore(petStore2);
        fish2.setPetStore(petStore2);
        fish1.setPetStore(petStore3);

        // bidirection
        petStore1.getAnimals().add(cat1);
        petStore1.getAnimals().add(cat2);
        petStore1.setAnimals(new HashSet<>(Arrays.asList(cat1, cat2)));

        petStore2.getAnimals().add(cat3);
        petStore2.getAnimals().add(fish2);
        petStore2.setAnimals(new HashSet<>(Arrays.asList(cat3, fish2)));

        petStore3.getAnimals().add(fish1);
        petStore3.setAnimals(new HashSet<>(Arrays.asList(fish1)));

        animalRepository.save(cat1);
        animalRepository.save(cat2);
        animalRepository.save(cat3);
        animalRepository.save(fish2);
        animalRepository.save(fish1);

        petStoreRepository.save(petStore1);
        petStoreRepository.save(petStore2);
        petStoreRepository.save(petStore3);

        // Creation des produits
        Product laisse = new Product("LAI0083", "laisse pour médor", ProdType.ACCESSORY, 35.99);
        Product brossePoilCourt = new Product("BPC0089", "Brosse à poil court", ProdType.CLEANING, 28.99);
        Product brossePoilLong = new Product("BPL0056", "Brosse à poil long", ProdType.CLEANING, 30.99);
        Product RC_chiot = new Product("RCCHI0054", "Royal Canin pour chiots", ProdType.FOOD, 70.99);
        Product RC_chat = new Product("RCCHA0008", "Royal Canin pour chats", ProdType.FOOD, 60.99);
        Product  Hills_GrosChien= new Product("HILGC4083", "Royal Canin pour gros chien", ProdType.FOOD, 80.99);
        Product balleRebon = new Product("BREB0433", "balle rebondissante", ProdType.ACCESSORY, 10.99);
        Product quickquick = new Product("QUI0023", "jouet sonore", ProdType.ACCESSORY, 12.99);
        Product plume = new Product("PlU0085", "plume pour chat", ProdType.ACCESSORY, 8.99);

        //save des produits
        productRepository.saveAll(List.of(
                laisse, brossePoilCourt, brossePoilLong, RC_chiot, RC_chat,
                Hills_GrosChien, balleRebon, quickquick, plume
        ));

        //ajout des produits pour le store 1
        List<Product> productsToAddPetStore1 = List.of(laisse, RC_chiot, balleRebon, quickquick);
        for (Product product : productsToAddPetStore1) {
            petStore1.addProductToPetStore(product);
        }
        petStoreRepository.save(petStore1);

        List<Product> productsToAddPetStore2 = List.of(Hills_GrosChien, RC_chiot, plume, quickquick);
        for (Product product : productsToAddPetStore2) {
            petStore2.addProductToPetStore(product);
        }
        petStoreRepository.save(petStore2);

        List<Product> productsToAdd = List.of(RC_chat, brossePoilLong, balleRebon, brossePoilCourt);
        for (Product product : productsToAdd) {
            petStore3.addProductToPetStore(product);
        }

        petStoreRepository.save(petStore3);

        // print des stores
        System.out.println("PetStore 1: " + petStore1 +
                " | ADRESSE LIEE : " + petStore1.getAddress() + "\n" +
                " | ANIMEAUX DISPO : " + petStore1.getAnimals() + "\n" +
                " | PRODUIT PAR PETSTORE : " + petStore1.getProducts());

        System.out.println("PetStore 2: " + petStore2 +
                " | ADRESSE LIEE : " + petStore2.getAddress() + "\n" +
                " | ANIMEAUX DISPO : " + petStore2.getAnimals() + "\n" +
                " | PRODUIT PAR PETSTORE : " + petStore2.getProducts());

        System.out.println("PetStore 3 " + petStore3 +
                " | ADRESSE LIEE : " + petStore3.getAddress() + "\n" +
                " | ANIMEAUX DISPO : " + petStore3.getAnimals() + "\n" +
                " | PRODUIT PAR PETSTORE : " + petStore3.getProducts());


        //appel de la query pour obtenir tous les animeaux d un store (ici store 1)
        Long petStoreId = 1L;
        List<Animal> animals = animalRepository.findByPetStoreId(petStoreId);

        System.out.println("LISTE D'ANIMEAUX POUR LE STORE" + petStoreId + " :");
        animals.forEach(System.out::println);


    }
}























