package com.petshop.petshop.Repository;

import com.petshop.petshop.BO.PetStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetStoreRepository extends JpaRepository<PetStore, Long> {}
