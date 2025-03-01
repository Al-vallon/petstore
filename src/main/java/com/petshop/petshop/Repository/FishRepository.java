package com.petshop.petshop.Repository;

import com.petshop.petshop.BO.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {
}
