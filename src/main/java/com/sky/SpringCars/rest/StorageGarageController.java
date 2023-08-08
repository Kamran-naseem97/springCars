package com.sky.SpringCars.rest;

import com.sky.SpringCars.Domain.Car;
import com.sky.SpringCars.Domain.storageGarage;
import com.sky.SpringCars.services.storageGarageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storageGarage")
public class StorageGarageController {
    private storageGarageService service;

    public StorageGarageController(storageGarageService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<storageGarage> getAll(){
        return this.service.getAll();
    }

    @GetMapping("/get/{id}")
    public storageGarage getById(@PathVariable int id){
        System.out.println("Garage with index " + id + " " + this.service.getById(id));
        return this.service.getById(id);
    }

    @PostMapping("/create")
    public HttpEntity<storageGarage> create(@RequestBody storageGarage garage){
        return new ResponseEntity<>( this.service.create(garage), HttpStatus.CREATED);
    }

    @PostMapping("createMultiple")
    public Car create(@RequestBody List<storageGarage> newGarages){
        this.service.create(newGarages);
        return null;
    }

    @PatchMapping("/update/{id}")
    public storageGarage update(
            @PathVariable int id,
            @RequestParam (value = "address", required = false) String address){
        return this.service.update(id, address);
    }

    @DeleteMapping("/remove/{id}")
    public storageGarage remove(@PathVariable int id){
        storageGarage deleteGarage = this.service.getById(id);
        this.service.remove(id);
        return deleteGarage;
    }

}
