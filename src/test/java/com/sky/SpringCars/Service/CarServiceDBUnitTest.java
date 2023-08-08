package com.sky.SpringCars.Service;

import com.sky.SpringCars.Domain.Car;
import com.sky.SpringCars.repos.carRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.sky.SpringCars.services.carService;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CarServiceDBUnitTest {

    @Autowired
    private carService service;

    @MockBean
    private carRepo repo;

    @Test
    void testUpdate(){
        //GIVEN
        int id = 1;
        Car existing = new Car(id, "BMW M2 Comp", 410);
        String newMakeModel = "Pagani Huayra";
        int newPower = 764;
        Car updated = new Car(id, newMakeModel, newPower);

        //WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(existing));
        Mockito.when(this.repo.save(updated)).thenReturn(updated);

        // THEN
        Assertions.assertEquals(updated, this.service.update(id, newMakeModel, newPower));
        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
        Mockito.verify(this.repo, Mockito.times(1)).save(updated);
    }

    @Test
    void testDelete(){
        int id = 2;
        Car toDel = new Car(id, "Fiat Multipla", 2);

        //WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(toDel));

        Assertions.assertEquals(toDel, this.service.remove(id));

        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
    }

    @Test
    void testCreate(){
        int id = 3;
        String makeModel = "Ferrari F40";
        int power = 471;

        Car newCar = new Car(id, makeModel, power);
        Mockito.when(this.repo.save(newCar)).thenReturn(newCar);
        Assertions.assertEquals(newCar, this.service.create(new Car(id, makeModel, power)));
    }

    @Test
    void testFindByMakeModel(){
        final String search = "Toyota";

        List<Car> cars = new ArrayList<>();
        Car c1 = new Car(1,"Toyota Supra", 380);
        Car c2 = new Car(2, "BMW M2 Comp", 410);
        cars.add(c1);
        cars.add(c2);

        Mockito.when(this.repo.findBymakeModelContainsIgnoreCase(search)).thenReturn(cars);
        Assertions.assertEquals(cars, this.service.findBymakeModel(search));
        Mockito.verify(this.repo, Mockito.times(1)).findBymakeModelContainsIgnoreCase(search);
    }

    @Test
    void testFindPower(){
        final String search = "Toyota";
        final int powers = 380;

        Mockito.when(this.repo.findPower(search)).thenReturn(powers);

        Assertions.assertEquals(powers, this.service.findPower(search));
        Mockito.verify(this.repo,Mockito.times(1)).findPower(search);
    }
}
