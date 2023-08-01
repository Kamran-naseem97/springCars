package com.sky.SpringCars.services;

import com.sky.SpringCars.Domain.Car;
import com.sky.SpringCars.repos.carRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class CarServiceDB implements carService{
    private carRepo repo;

    public CarServiceDB(carRepo repo) {
        this.repo = repo;
    }

    @Override
    public Car getById(Integer id) {
        Optional<Car> optionalCar = this.repo.findById(id);
        Car actualCar = optionalCar.get();
        return actualCar;
    }

    @Override
    public Car create(Car car) {
        return this.repo.save(car);
    }

    @Override
    public List<Car> create(List<Car> newCar) {
        return this.repo.saveAll(newCar);
    }

    @Override
    public List<Car> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Car update(Integer id, String makeModel, Integer power) {
        Car toUp = this.getById(id);
        if (makeModel != null) toUp.setMakeModel(makeModel);
        if (power != null) toUp.setPower(power);
        return this.repo.save(toUp);
    }

    @Override
    public Car remove(Integer id) {
        Car toDel = this.getById(id);
        this.repo.deleteById(id.intValue());
        return toDel;
    }

    @Override
    public List<Car> findBymakeModel(String makeModel) {
        return this.repo.findBymakeModelContainsIgnoreCase(makeModel);
    }

    @Override
    public Integer findPower(String makeModel) {
        return this.repo.findPower(makeModel);
    }
}
