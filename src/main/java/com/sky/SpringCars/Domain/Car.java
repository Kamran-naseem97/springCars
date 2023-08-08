package com.sky.SpringCars.Domain;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = false, nullable = true)
    private String makeModel;
    private Integer power;

    @ManyToOne(targetEntity = storageGarage.class)
    private storageGarage garage;

    public Car( String makeModel, Integer power) {
        this.makeModel = makeModel;
        this.power = power;
    }

    public Car(int id, String makeModel, Integer power) {
        this.id = id;
        this.makeModel = makeModel;
        this.power = power;
    }

    //    Default constructor
    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public storageGarage getGarage() {
        return garage;
    }

    public void setGarage(storageGarage garage) {
        this.garage = garage;
    }

    @Override
    public String toString() {
        return "Car{" +
                "makeModel='" + makeModel + '\'' +
                ", power=" + power +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Objects.equals(makeModel, car.makeModel) && Objects.equals(power, car.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, makeModel, power);
    }
}
