package com.referbruv.swaggerheroes.controllers;

import com.referbruv.swaggerheroes.models.dto.CreateHero;
import com.referbruv.swaggerheroes.models.entities.Hero;
import com.referbruv.swaggerheroes.repositories.HeroRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/heroes")
public class HeroesController {
    private HeroRepository repository;

    @GetMapping("")
    public ResponseEntity<List<Hero>> getAllHeroes() {
        var heroes = repository.findAll();
        return ResponseEntity.ok(heroes);
    }

    @GetMapping("{id}")
    public ResponseEntity<Hero> findHero(@PathVariable("id") int heroId) {
        var hero = repository.findById(heroId);
        if(!hero.isPresent()) return ResponseEntity.notFound().build();
        var entity = hero.get();
        return ResponseEntity.ok(entity);
    }

    @PostMapping("")
    public ResponseEntity<Integer> createHero(@RequestBody CreateHero model) {
        var hero = repository.saveAndFlush(new Hero(model.getName(), model.getBio()));
        return ResponseEntity.ok(hero.getHeroId());
    }

    @PutMapping("{id}")
    public ResponseEntity<Integer> updateHero(@PathVariable("id") int heroId, @RequestBody CreateHero model) {
        var hero = repository.findById(heroId);
        if(!hero.isPresent()) return ResponseEntity.notFound().build();
        var entity = hero.get();
        entity.setName(model.getName());
        entity.setBio(model.getBio());
        repository.saveAndFlush(entity);
        return ResponseEntity.ok(entity.getHeroId());
    }

    @DeleteMapping
    public ResponseEntity deleteHero(@PathVariable("{id}") int heroId) {
        var hero = repository.findById(heroId);
        if(!hero.isPresent()) return ResponseEntity.notFound().build();
        var entity = hero.get();
        repository.delete(entity);
        repository.flush();
        return ResponseEntity.ok().build();
    }
}
