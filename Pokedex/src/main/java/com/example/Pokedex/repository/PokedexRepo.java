package com.example.Pokedex.repository;

import com.example.Pokedex.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Using h2 for an in memory database
@Repository
public interface PokedexRepo extends JpaRepository<Entry,Long> {
}
