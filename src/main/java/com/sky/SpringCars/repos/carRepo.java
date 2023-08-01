package com.sky.SpringCars.repos;

import com.sky.SpringCars.Domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface carRepo extends JpaRepository<Car, Integer> {
    List<Car> findBymakeModelContainsIgnoreCase(String makeModel);

    // JPQL -> Java Persistence Query Language
    @Query(value = "SELECT POWER FROM CAR WHERE make_Model LIKE '%?1%'", nativeQuery = true)
//    @Query("SELECT c.power FROM Car h WHERE c.makeModel = ?1 ")
    Integer findPower(String makeModel);
}

