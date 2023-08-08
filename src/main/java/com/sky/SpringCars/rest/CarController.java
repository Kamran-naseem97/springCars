package com.sky.SpringCars.rest;

import com.sky.SpringCars.Domain.Car;
import com.sky.SpringCars.services.carService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    public CarController(carService service) {
        this.service = service;
    }
    private carService service;

//    sending any get request to this URL, /hello that will call the method hello() to return whatever the method sets
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/getAll")
    public List<Car> getAll(){
        return this.service.getAll();
    }

    @GetMapping("/get/{id}")
    public Car getById(@PathVariable int id){
        System.out.println("Car with index " + id + " " + this.service.getById(id));
        return this.service.getById(id);
    }
//    @GetMapping("/findByName/{name}")
//    public List<Hedgehog> findByName(@PathVariable String name) {
//        return this.service.findByName(name);
//    }
    @GetMapping("/findPower/{name}")
    public Integer findPower(@PathVariable String name) {
        return this.service.findPower(name);
    }

    @GetMapping("/findBymakeModel/{makeModel}")
    public List<Car> findBymakeModel(@PathVariable String makeModel) {
        return this.service.findBymakeModel(makeModel);
    }

    @PostMapping("/create")
    public HttpEntity<Car> create(@RequestBody Car car){
        return new ResponseEntity<>( this.service.create(car), HttpStatus.CREATED);
    }

    @PostMapping("createMultiple")
    public Car create(@RequestBody List<Car> newCars){
        this.service.create(newCars);
        return null;
    }

    @PatchMapping("/update/{id}")
    public Car update(
            @PathVariable int id,
            @RequestParam (value = "makeModel", required = false) String makeModel,
            @RequestParam (value = "power", required = false) Integer power){
        return this.service.update(id, makeModel, power);
    }

    @DeleteMapping("/remove/{id}")
    public Car remove(@PathVariable int id){
        Car deleteCar = this.service.getById(id);
        this.service.remove(id);
        return deleteCar;
    }

}
