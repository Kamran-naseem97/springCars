package com.sky.SpringCars.services;

import com.sky.SpringCars.Domain.Car;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Primary
@Service
public class CarServiceList implements carService{

    private List<Car> garage = new ArrayList<>();

    //    sending any get request to this URL, /hello that will call the method hello() to return whatever the method sets
    @Override
    public List<Car> getAll(){
        return List.copyOf(this.garage);
    }

    @Override
    public Car getById(Integer id){
        System.out.println("Car with index " + id + " " + this.garage.get(id));
        return this.garage.get(id);
    }

    @Override
    public Car create(Car car){
        System.out.println("Received " + car);
        this.garage.add(car);
        Car created = this.garage.get(this.garage.size() -1);
        return created;
    }

    @Override
    public  List<Car> create(List<Car> newCars){
        if (this.garage.addAll(newCars)){
            return newCars;
        } else {
            return null;
        }
    }


    @Override
    public Car update( Integer id, String makeModel, Integer power){
        this.garage.get(id).setMakeModel(makeModel);
        this.garage.get(id).setPower(power);
        return this.garage.get(id);
    }

    @Override
    public Car remove(Integer id){
        Car deleteCar = this.garage.get(id);
        this.garage.remove((Integer) id.intValue());
        return deleteCar;
    }

    @Override
    public List<Car> findBymakeModel(String makeModel) {
        return null;
    }

    @Override
    public Integer findPower(String makeModel) {
        return null;
    }


//    Needs refactoring to above
//    @Override
//    public List<Hedgehog> findByName(String name) {
//        List<Hedgehog> found = new ArrayList<>();
//        for (Hedgehog h : this.hedgehogs) {
//            if (name.equals(h.getName())) found.add(h);
//        }
//        return found;
//    }
//
//    @Override
//    public Integer findAgeByName(String name) {
//        return null;
//    }

}
