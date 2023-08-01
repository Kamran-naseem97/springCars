package com.sky.SpringCars.Domain;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = false, nullable = true)
    private String makeModel;
    private Integer power;

    public Car(Integer id, String makeModel, Integer power) {
        this.id = id;
        this.makeModel = makeModel;
        this.power = power;
    }

//    Default constructor
    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Car{" +
                "makeModel='" + makeModel + '\'' +
                ", power=" + power +
                '}';
    }
}
