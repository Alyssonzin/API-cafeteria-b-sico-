package com.alysson.api_coffee_shop.controllers;

import com.alysson.api_coffee_shop.models.Coffee;
import com.alysson.api_coffee_shop.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    CoffeeRepository repository;

    @GetMapping("/all")
    public List<Coffee> getAllCoffee() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Coffee getCoffee(@PathVariable Long id){
        Optional<Coffee> coffee = repository.findById(id);

        if(coffee.isPresent()){
            return coffee.get();
        }
        return null;
    }

    @PostMapping("/create")
    public Coffee createCoffee(@RequestBody Coffee coffee) {
        Coffee newCoffee = new Coffee();

        newCoffee.setName(coffee.getName());
        newCoffee.setPrice(coffee.getPrice());

        return repository.save(newCoffee);
    }

    @PutMapping("/update/{id}")
    public Coffee updateCoffee(@RequestBody Coffee coffee, @PathVariable Long id){
        Coffee updatedCoffee = repository.findById(id).orElseThrow();

        updatedCoffee.setName(coffee.getName());
        updatedCoffee.setPrice(coffee.getPrice());

        return repository.save(updatedCoffee);
    }

    @DeleteMapping("/delete/{id}")
    public Coffee deleteCoffee(@PathVariable Long id) {
        Coffee deletedCoffee = repository.findById(id).orElseThrow();
        repository.delete(deletedCoffee);

        return deletedCoffee;
    }
}
