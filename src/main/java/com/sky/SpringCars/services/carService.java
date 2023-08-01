package com.sky.SpringCars.services;

import com.sky.SpringCars.Domain.Car;

import java.util.ArrayList;
import java.util.List;

public interface carService {
    Car getById(Integer id);
    Car create(Car car);
    List<Car> create(List<Car> newCar);
    List<Car> getAll();
    Car update(Integer id, String makeModel, Integer power);
    Car remove(Integer id);
    List<Car> findBymakeModel(String makeModel);
    Integer findPower(String makeModel);
}