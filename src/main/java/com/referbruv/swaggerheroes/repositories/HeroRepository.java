package com.referbruv.swaggerheroes.repositories;

import com.referbruv.swaggerheroes.models.entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> { }
