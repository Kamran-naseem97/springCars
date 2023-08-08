package com.sky.SpringCars.services;

import com.sky.SpringCars.Domain.Car;

import java.util.ArrayList;
import java.util.List;

public interface carService {
    Car getById(int id);
    Car create(Car car);
    List<Car> create(List<Car> newCar);
    List<Car> getAll();
    Car update(int id, String makeModel, Integer power);
    Car remove(int id);
    List<Car> findBymakeModel(String makeModel);
    Integer findPower(String makeModel);
}