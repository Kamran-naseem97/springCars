package com.sky.SpringCars.services;

import com.sky.SpringCars.Domain.storageGarage;
import com.sky.SpringCars.repos.storageGarageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class storageGarageService {
    private storageGarageRepo repo;

    public storageGarageService(storageGarageRepo repo) {
        this.repo = repo;
    }

    public List<storageGarage> getAll() {
        return repo.findAll();
    }

    public storageGarage create(storageGarage garage){
        return this.repo.save(garage);
    }
    public List<storageGarage> create(List<storageGarage> newGarages){
        return this.repo.saveAll(newGarages);
    }

    public storageGarage getById(int id) {
        storageGarage actualGarage = this.repo.findById(id).get();
        return actualGarage;
    }

    public storageGarage update(int id, String address){
        storageGarage toUpdate = this.getById(id);

        if(address != null) toUpdate.setAddress(address);
        return this.repo.save(toUpdate);
    }

    public storageGarage remove(int id){
        storageGarage toDelete = this.getById(id);
        this.repo.deleteById(id);
        return toDelete;
    }
}
