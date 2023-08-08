package com.sky.SpringCars.repos;

import com.sky.SpringCars.Domain.storageGarage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface storageGarageRepo extends JpaRepository<storageGarage, Integer> {
}
