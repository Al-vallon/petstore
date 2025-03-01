# 🐾 PetStore Project

## 📌 Description

Ce projet est une application multi-couches permettant de gérer une animalerie en utilisant JPA et une base de données H2 en mémoire. Il inclut :

- 🗄️ Une base de données nommée `petstore`
- 🏗️ Un mapping des entités JPA avec les relations et une stratégie d'héritage `Joined`
- 🔄 Une série d'instructions pour manipuler et interroger la base de données via `EntityManager`

## ✨ Fonctionnalités

- 📜 Création automatique du schéma de base de données
- 🔗 Mapping des entités avec `@OneToMany`, `@ManyToMany`, `@ManyToOne`
- 🏛️ Stratégie d'héritage `@Inheritance(strategy = InheritanceType.JOINED)`
- 📥 Insertion de données de test
- 🔍 Requêtage des animaux d’une animalerie donnée

## 📋 Prérequis

- ☕ **JDK 17+**
- 🛠️ **Maven**
- 🐙 **Git**

## 🚀 Installation

1. Cloner le projet :
   ```sh
   git clone https://github.com/Al-vallon/petstore.git
   cd petstore
   ```
2. Vérifier la configuration de la base de données dans `src/main/resources/application.properties` :
   ```properties
   spring.datasource.url=jdbc:h2:mem:petstore
   spring.datasource.username=SA
   spring.datasource.password=pass
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   ```

## ▶️ Exécution

Lancer l'application via le fichier `PetShopApplication.java` :

```sh
mvn spring-boot:run
```

L'application démarre avec une base H2 en mémoire et insère automatiquement des données de test.

## 🖥️ Accès à la base de données H2

L'interface H2 est accessible via :

```sh
http://localhost:8081/h2
```

- 🔑 **JDBC URL** : `jdbc:h2:mem:petstore`
- 👤 **Username** : `SA`
- 🔒 **Password** : `pass`

## 📂 Structure du projet

```
petstore/
├── src/main/java/com/example/petstore/
│   ├── entity/        # 📦 Entités JPA
│   ├── repository/    # 🗄️ Interfaces JPA Repository
│   ├── PetShopApplication.java # 🎬 Point d'entrée de l'application
├── src/main/resources/
│   ├── application.properties
├── pom.xml # 📜 Fichier de configuration Maven
```

## ✍️ Auteur

Projet développé par **Alexandre Vallon** dans le cadre d'un exercice de mapping JPA.

---

